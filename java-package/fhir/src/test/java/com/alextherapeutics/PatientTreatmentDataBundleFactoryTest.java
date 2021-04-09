package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;
import com.alextherapeutics.model.NicotineTreatmentQuestionnaire;
import com.alextherapeutics.model.PatientTreatmentData;
import com.alextherapeutics.model.SelfReportedSmokingStatusCode;
import com.alextherapeutics.model.TriggerCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Questionnaire;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

class PatientTreatmentDataBundleFactoryTest {
    private static PatientTreatmentDataBundleFactory factory;
    private static FhirContext context;

    @BeforeAll
    static void first() {
        context = FhirContext.forR4();
        factory = new PatientTreatmentDataBundleFactory(context);
    }

    @Test
    void testCreateBundle() {
        var questionnaire = new NicotineTreatmentQuestionnaire();
        questionnaire.setTitle("Logging a trigger");
        questionnaire.setUrl("http://www.alextherapeutics.com/fhir/Questionnaire/trigger");
        questionnaire.setStatus(Enumerations.PublicationStatus.ACTIVE);
        questionnaire.setDescription(
                "This questionnaire asks the patient what they are feeling or which situation they are in right now"
        );
        questionnaire.setPurpose(
                "This enables the patient to understand their own triggers, as well as for the DiGA to gain information on which triggers are common for this patient"
        );
        questionnaire.addItem(
                new Questionnaire.QuestionnaireItemComponent()
                        .setLinkId("TRIGGER")
                        .setText("In what situation are you right now?")
                        .setType(Questionnaire.QuestionnaireItemType.CHOICE)
                        .setAnswerValueSet("http://www.alextherapeutics.com/fhir/ValueSet/trigger-code")
        );
        var b = factory.createBundle(
                PatientTreatmentData.builder()
                        .digaName("Eila")
                        .digaPlanDescription("A treatment plan using the Eila DiGA.\\nThis is a CBT-based treatment plan for nicotine usage where the patient progress towards quitting smoking by cutting down on cigarettes for a period of time.\\nThe patient has activities like logging their cigarettes, their triggers, and finding out which interventions are effective for them.\\n... And so on (example)")
                        .digaTreatmentStartDate(new Date())
                        .patientName("Max")
                        .patientEmail("max.mustermann@diga.de")
                        .patientGender(Enumerations.AdministrativeGender.MALE)
                        .organizationName("Alex Therapeutics")
                        .commonNicotineTriggers(
                                Arrays.asList(TriggerCode.alcohol, TriggerCode.anxious)
                        )
                        .effectiveNicotineInterventions(Collections.singletonList("drink water"))
                        .smokingStatusList(
                                Arrays.asList(
                                        PatientTreatmentData.SelfReportedSmokingStatus.builder()
                                                .reportedOn(new Date())
                                                .status(SelfReportedSmokingStatusCode.cutting_down)
                                                .build(),
                                        PatientTreatmentData.SelfReportedSmokingStatus.builder()
                                                .reportedOn(new Date(System.currentTimeMillis() - 10000))
                                                .status(SelfReportedSmokingStatusCode.actively_smoking)
                                                .build()
                                )
                        )
                        .questionnaireResponses(
                                Collections.singletonList(
                                        PatientTreatmentData.NicotineTreatmentQuestionnaireResponse.builder()
                                                .questionnaire(questionnaire)
                                                .items(Collections.singletonList(new QuestionnaireResponse.QuestionnaireResponseItemComponent().addItem(
                                                        new QuestionnaireResponse.QuestionnaireResponseItemComponent()
                                                                .setLinkId("TRIGGER")
                                                                .setAnswer(Arrays.asList(
                                                                        new QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent()
                                                                                .setValue(
                                                                                        new Coding()
                                                                                                .setSystem("http://www.alextherapeutics.com/fhir/CodeSystem/trigger-code-system")
                                                                                                .setCode(TriggerCode.waiting.toString()
                                                                                                )))))))
                                                .build()
                                )
                        )
                        .build()
        );


    }

}