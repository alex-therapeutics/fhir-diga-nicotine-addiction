package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        var b = factory.createBundle(ExamplePatientTreatmentData.get());
    }

}