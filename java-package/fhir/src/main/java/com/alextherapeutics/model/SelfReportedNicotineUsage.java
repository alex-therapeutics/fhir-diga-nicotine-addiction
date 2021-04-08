package com.alextherapeutics.model;

import com.alextherapeutics.annotation.CompositeExtensionFromFhir;
import com.alextherapeutics.annotation.ExtensionFromFhir;
import com.alextherapeutics.annotation.GeneratedFromFhir;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateType;

@GeneratedFromFhir(
        id = "self-reported-nicotine-usage",
        compositeExtensions = {
                @CompositeExtensionFromFhir(
                        id = "self-reported-smoking-status",
                        name = "currentSelfReportedSmokingStatus",
                        extensions = {
                                @ExtensionFromFhir(
                                        name = "reportedOn",
                                        valueType = DateType.class
                                ),
                                @ExtensionFromFhir(
                                        name = "status",
                                        valueType = CodeableConcept.class
                                )
                        }
                )
        }
)
public interface SelfReportedNicotineUsage {
}
