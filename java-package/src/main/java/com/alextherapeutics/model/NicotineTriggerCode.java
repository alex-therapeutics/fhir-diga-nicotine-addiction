package com.alextherapeutics.model;

import com.alextherapeutics.constants.DefinitionUri;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class NicotineTriggerCode {
    public static CodeableConcept fromCode(TriggerCodeSystemCode code) {
        var codeable = new CodeableConcept();
        codeable.addCoding(
                new Coding(
                        DefinitionUri.TRIGGER_CODE_SYSTEM,
                        code.getCode(),
                        code.getDisplay()
                )
        );
        return codeable;
    }
}
