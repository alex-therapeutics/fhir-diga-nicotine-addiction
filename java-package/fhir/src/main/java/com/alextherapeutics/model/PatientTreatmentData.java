package com.alextherapeutics.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Object to create a Bundle for exporting one patient's data.
 */
@Builder
@Getter
public class PatientTreatmentData {
    // diga data
    @NonNull
    private String digaName;
    private String organizationName;
    /**
     * A description of what the DiGA's treatment plan does.
     */
    @NonNull
    private String digaPlanDescription;

    @NonNull
    private String patientName;
    private String patientEmail;
    private Enumerations.AdministrativeGender patientGender;
    @Builder.Default
    private List<TriggerCode> commonNicotineTriggers = new ArrayList<>();
    @Builder.Default
    private List<String> effectiveNicotineInterventions = new ArrayList<>();

    @Builder.Default
    private List<SelfReportedSmokingStatus> smokingStatusList = new ArrayList<>();

    /**
     * When the patient started their treatment using the DiGA. This becomes the CarePlan's {@link CarePlan#getCreated()} value.
     */
    @NonNull
    private Date digaTreatmentStartDate;

    /**
     * Attach patient responses to questionnaires. You only need to add the questionnaire items (with answers etc)
     */
    @Builder.Default
    private List<NicotineTreatmentQuestionnaireResponse> questionnaireResponses = new ArrayList<>();

    @Builder
    @Getter
    public static class SelfReportedSmokingStatus {
        @NonNull
        private Date reportedOn;
        @NonNull
        private SelfReportedSmokingStatusCode status;
    }

    @Builder
    @Getter
    public static class NicotineTreatmentQuestionnaireResponse {
        /**
         * The questionnaire that was responded to.
         * Make sure to set the canonical 'url' field.
         */
        private NicotineTreatmentQuestionnaire questionnaire;
        /**
         * Items containing answers to the questionnaire.
         * Other fields on the QuestionnaireResponse such as source, questionnaire, etc. are set automatically based
         * on other data.
         */
        private List<QuestionnaireResponse.QuestionnaireResponseItemComponent> items;
    }
}
