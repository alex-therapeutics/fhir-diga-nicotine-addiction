package com.alextherapeutics;

import com.alextherapeutics.model.TriggerCodeSystem;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.hl7.fhir.r4.model.Enumerations;

import java.util.ArrayList;
import java.util.List;

/**
 * Object to create a Bundle for exporting one patient's data.
 */
@Builder
@Getter
public class PatientTreatmentData {
    @NonNull
    private String digaName;
    private String organizationName;

    @NonNull
    private String patientName;
    private String patientEmail;
    private Enumerations.AdministrativeGender patientGender;
    @Builder.Default
    private List<TriggerCodeSystem> commonNicotineTriggers = new ArrayList<>();
    @Builder.Default
    private List<String> effectiveNicotineInterventions = new ArrayList<>();
}
