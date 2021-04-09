package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;
import com.alextherapeutics.model.TriggerCodeSystem;
import org.hl7.fhir.r4.model.Enumerations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

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
        var b = factory.createBundle(
                PatientTreatmentData.builder()
                        .digaName("Eila")
                        .patientName("Max")
                        .patientEmail("max.mustermann@diga.de")
                        .patientGender(Enumerations.AdministrativeGender.MALE)
                        .organizationName("Alex Therapeutics")
                        .commonNicotineTriggers(
                                Arrays.asList(TriggerCodeSystem.alcohol, TriggerCodeSystem.anxious)
                        )
                        .effectiveNicotineInterventions(Collections.singletonList("drink water"))
                        .build()
        );

        var parser = context.newXmlParser().setPrettyPrint(true);

        var res = parser.encodeResourceToString(b);
        int i = 0;

    }

}