package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;
import com.alextherapeutics.model.TriggerCodeSystem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
                        .organizationName("Alex Therapeutics")
                        .commonNicotineTriggers(
                                Arrays.asList(TriggerCodeSystem.alcohol)
                        )
                        .build()
        );

        var parser = context.newXmlParser().setPrettyPrint(true);

        var res = parser.encodeResourceToString(b);
        int i = 0;

    }

}