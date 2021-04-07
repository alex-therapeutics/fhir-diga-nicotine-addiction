package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.FhirValidator;
import com.alextherapeutics.model.NicotineTriggerCode;
import com.alextherapeutics.model.SelfReportedNicotineUsingPatient;
import com.alextherapeutics.model.TriggerCodeSystemCode;
import org.hl7.fhir.r4.model.StringType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        patient.getCommonNicotineTrigger().add(NicotineTriggerCode.fromCode(TriggerCodeSystemCode.WAITING));
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