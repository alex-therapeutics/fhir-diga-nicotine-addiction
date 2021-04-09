package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.util.BundleBuilder;
import com.alextherapeutics.model.ExportedNicotineUsageTreatmentData;
import com.alextherapeutics.model.PatientTreatmentDataFhir;
import com.alextherapeutics.model.SelfReportedNicotineUsingPatient;
import lombok.AllArgsConstructor;
import org.hl7.fhir.instance.model.api.IBase;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.r4.model.*;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PatientTreatmentDataBundleFactory {
    private FhirContext context;

    public IBaseBundle createBundle(PatientTreatmentData data) {
        var builder = new BundleBuilder(context);
        builder.setBundleField("type", Bundle.BundleType.DOCUMENT.toCode());
        builder.setBundleField("id", UUID.randomUUID().toString()); // random here b/c we do not track bundles sent
        builder.setBundleField("timestamp", new DateTimeType(new Date()).getValueAsString());

        var fhirTreatmentData = createFhirTreatmentData(data);

        var composition = createComposition(fhirTreatmentData, data);
        addEntry(builder, composition);

        if (fhirTreatmentData.getOrganization() != null) {
            addEntry(builder, fhirTreatmentData.getOrganization());
        }
        addEntry(builder, fhirTreatmentData.getPatient());

        return builder.getBundle();
    }
    private void addEntry(BundleBuilder builder, IBase resource) {
        var entry = builder.addEntry();

        builder.addToEntry(
                entry,
                "resource",
                resource
        );
    }
    private PatientTreatmentDataFhir createFhirTreatmentData(PatientTreatmentData data) {
        return PatientTreatmentDataFhir.builder()
                .organization(
                        (Organization) new Organization()
                                .setName(data.getOrganizationName())
                                .setId(data.getOrganizationName())
                )
                .patient(createPatient(data))
                .build();
    }
    private SelfReportedNicotineUsingPatient createPatient(PatientTreatmentData data) {
        var patient = new SelfReportedNicotineUsingPatient();
        patient.setCommonNicotineTrigger(
                data.getCommonNicotineTriggers().stream().map(trigger -> new CodeableConcept(new Coding().setCode(trigger.toString())))
                        .collect(Collectors.toList())
        );
        return patient;
    }
    private ExportedNicotineUsageTreatmentData createComposition(PatientTreatmentDataFhir fhirData, PatientTreatmentData data) {
        var composition = new ExportedNicotineUsageTreatmentData();
        composition.setStatus(Composition.CompositionStatus.FINAL);
        composition.setType(new CodeableConcept().setTextElement(new StringType("DiGA Data Export")));
        composition.setSubject(new Reference(fhirData.getPatient()));
        composition.setDate(new Date());
        composition.setTitle("Treatment data export from DiGA \"" + data.getDigaName() + "\" for patient " + data.getPatientName());
        if (fhirData.getOrganization() != null) {
            composition.setAuthor(
                    Collections.singletonList(
                            new Reference(
                                    fhirData.getOrganization()
                            )
                    )
            );
        }
        composition.setSection(createCompositionSections(fhirData));
        return composition;
    }
    private List<Composition.SectionComponent> createCompositionSections(PatientTreatmentDataFhir fhirData) {
        var patientData = new Composition.SectionComponent()
                .setTitle("Patient data for a self-reporting nicotine-using patient")
                .addEntry(new Reference(fhirData.getPatient()));

        var conditionData = new Composition.SectionComponent()
                .setTitle("Self-reported nicotine-usage condition")
                .addEntry(new Reference(fhirData.getCondition()));

        var planData = new Composition.SectionComponent()
                .setTitle("Nicotine usage treatment plan")
                .addEntry(new Reference(fhirData.getPlan()));

        var questionnaireData = fhirData.getQuestionnaires().stream()
                .map(
                        questionnaire -> new Composition.SectionComponent()
                                .setTitle("Questionnaire definition")
                                .addEntry(new Reference(questionnaire))
                ).collect(Collectors.toList());

        var responseData = fhirData.getQuestionnairesResponses().stream()
                .map(
                        response -> new Composition.SectionComponent()
                                .setTitle("Questionnaire responses")
                                .addEntry(new Reference(response))
                ).collect(Collectors.toList());

        var sections = Arrays.asList(
                patientData,
                conditionData,
                planData);
        sections.addAll(questionnaireData);
        sections.addAll(responseData);
        return sections;
    }
}
