package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.FhirValidator;
import com.alextherapeutics.model.SelfReportedNicotineUsingPatient;
import com.alextherapeutics.model.TriggerCodeSystem;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.StringType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ProfileSpecificValidatorFactoryTest {
    private static FhirValidator validator;

    @BeforeAll
    static void first() {
        validator = ProfileSpecificValidatorFactory.getValidator(FhirContext.forR4());
    }

    @Test
    void validatorValidatesExamplePatient() {
        var ctxt = FhirContext.forR4();
        var patient = new SelfReportedNicotineUsingPatient();
        patient.setActive(true);
        var codeable = new CodeableConcept();
        codeable.setCoding(Arrays.asList(new Coding().setCode(TriggerCodeSystem.coffee.getCode())));
        patient.getEffectiveNicotineIntervention().add(
                new StringType("drink-water")
        );
        var r = ctxt.newXmlParser().setPrettyPrint(true).encodeResourceToString(patient);
        var s = ctxt.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);

        Assertions.assertTrue(
                validator.validateWithResult(r).isSuccessful()
        );
        Assertions.assertTrue(
                validator.validateWithResult(s).isSuccessful()
        );
        Assertions.assertTrue(
                validator.validateWithResult(patient).isSuccessful()
        );
    }
}