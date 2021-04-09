package com.alextherapeutics.model;

import lombok.Builder;
import lombok.Getter;
import org.hl7.fhir.r4.model.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * The treatment data as package-specific FHIR resources
 * Excluding the composition, because it requires access to the other resources.
 */
@Builder
@Getter
public class PatientTreatmentDataFhir {
    private SelfReportedNicotineUsingPatient patient;
    private SelfReportedNicotineUsage condition;
    private NicotineUsageTreatmentPlan plan;
    @Builder.Default
    private List<NicotineTreatmentQuestionnaire> questionnaires = new ArrayList<>();
    @Builder.Default
    private List<NicotineTreatmentQuestionnaireResponse> questionnairesResponses = new ArrayList<>();
    private Organization organization;
}
