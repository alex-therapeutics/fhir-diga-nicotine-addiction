package com.alextherapeutics.model;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.util.ElementUtil;
import com.alextherapeutics.constants.DefinitionUri;
import lombok.Data;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;

import java.util.ArrayList;
import java.util.List;

@ResourceDef(name = "Patient", profile = DefinitionUri.SELF_REPORTED_NICOTINE_USING_PATIENT)
@Data
public class DeprSelfReportedNicotineUsingPatient extends Patient {

    @Child(name="commonNicotineTrigger", max=Child.MAX_UNLIMITED)
    @Extension(definedLocally = true, url = DefinitionUri.NICOTINE_TRIGGER, isModifier = false)
    @Description(shortDefinition = "Common trigger(s) for a patient to use nicotine")
    private List<CodeableConcept> commonNicotineTrigger = new ArrayList<>();

    @Child(name="effectiveNicotineIntervention", max=Child.MAX_UNLIMITED)
    @Extension(definedLocally = true, url = DefinitionUri.EFFECTIVE_INTERVENTION, isModifier = false)
    @Description(shortDefinition = "\"Intervention(s) that have been effective when patient has wanted to use nicotine")
    private List<StringType> effectiveNicotineIntervention = new ArrayList<>();

//        @Child(name="commonNicotineTrigger", max=Child.MAX_UNLIMITED)
//    @Extension(definedLocally = true, url = DefinitionUri.NICOTINE_TRIGGER, isModifier = false)
//    @Description(shortDefinition = "Common trigger(s) for a patient to use nicotine")
//    private List<org.hl7.fhir.r4.model.Extension> commonNicotineTrigger = new ArrayList<>();


    @Override
    public boolean isEmpty() {
        return super.isEmpty() && ElementUtil.isEmpty(commonNicotineTrigger, effectiveNicotineIntervention);
    }
}

// TODO - maybe define extension types in the interface somehow, or in a list in the annotation maybe (name - type map)