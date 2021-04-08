package com.alextherapeutics.model;

import com.alextherapeutics.annotation.ExtensionFromFhir;
import com.alextherapeutics.annotation.FhirType;
import com.alextherapeutics.annotation.GeneratedFromFhir;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.StringType;

@GeneratedFromFhir(
        id = "self-reported-nicotine-using-patient",
        type = FhirType.PROFILE,
        extensions = {
                @ExtensionFromFhir(name = "commonNicotineTrigger", valueType = CodeableConcept.class),
                @ExtensionFromFhir(name = "effectiveNicotineIntervention", valueType = StringType.class)
        }
)
public interface SelfReportedNicotineUsingPatient {
}
