package com.alextherapeutics.model;

import com.alextherapeutics.annotation.CompositeExtensionFromFhir;
import com.alextherapeutics.annotation.ExtensionFromFhir;
import com.alextherapeutics.annotation.GeneratedFromFhir;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateType;

@GeneratedFromFhir(
        id = "nicotine-usage-treatment-plan",
        compositeExtensions = {
                @CompositeExtensionFromFhir(
                        id = "self-reported-smoking-status",
                        name = "selfReportedSmokingStatus",
                        extensions = {
                        @ExtensionFromFhir(
                                name = "reportedOn",
                                valueType = DateType.class
                        ),
                        @ExtensionFromFhir(
                                name = "status",
                                valueType = CodeableConcept.class
                        )
                })
        }
        )
public interface NicotineUsageTreatmentPlan {
}
