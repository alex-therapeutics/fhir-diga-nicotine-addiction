package com.alextherapeutics.model;

import com.alextherapeutics.annotation.CompositeExtensionFromFhir;
import com.alextherapeutics.annotation.ExtensionFromFhir;
import com.alextherapeutics.annotation.FhirType;
import com.alextherapeutics.annotation.GeneratedFromFhir;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.StringType;

/**
 * Fields defined here are generated as classes by annotation processors
 * Classes will be generated with the field name as the class name,  f.e the {@link #ExportedNicotineUsageTreatmentData}
 * field will be generated as the {@link ExportedNicotineUsageTreatmentData} class based on the structure definitions
 * located in resources.
 *
 * The processor that does this is located in {@link com.alextherapeutics.annotation.GeneratedFromFhirProcessor}
 *
 * This class should not be intantiated or used in any other way than to define the resources for processing.
 */
class GeneratedResources {

    @GeneratedFromFhir(
            id = "exported-nicotine-usage-treatment-data"
    )
    private String ExportedNicotineUsageTreatmentData;

    @GeneratedFromFhir(
            id = "nicotine-treatment-questionnaire"
    )
    private String NicotineTreatmentQuestionnaire;

    @GeneratedFromFhir(
            id = "nicotine-treatment-questionnaire-response"
    )
    private String NicotineTreatmentQuestionnaireResponse;

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
    private String NicotineUsageTreatmentPlan;

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
    private String SelfReportedNicotineUsage;

    @GeneratedFromFhir(
        id = "self-reported-nicotine-using-patient",
        type = FhirType.PROFILE,
        extensions = {
                @ExtensionFromFhir(name = "commonNicotineTrigger", valueType = CodeableConcept.class),
                @ExtensionFromFhir(name = "effectiveNicotineIntervention", valueType = StringType.class)
        }
)
    private String SelfReportedNicotineUsingPatient;

    @GeneratedFromFhir(id = "trigger-code-system", type = FhirType.CODE_SYSTEM)
    private String TriggerCodeSystem;

    @GeneratedFromFhir(id = "self-reported-status-code-system", type = FhirType.CODE_SYSTEM)
    private String SelfReportedSmokingStatusCode;
}
