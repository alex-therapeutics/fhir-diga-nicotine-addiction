package com.alextherapeutics;

import com.alextherapeutics.model.TriggerCodeSystem;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

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

    private String patientName;
    @Builder.Default
    private List<TriggerCodeSystem> commonNicotineTriggers = new ArrayList<>();
}
