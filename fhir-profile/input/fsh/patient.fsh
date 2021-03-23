Profile: NicotineReducingPatient
Parent: Patient
* extension contains CommonNicotineTrigger named commonNicotineTrigger 0..*
* extension contains EffectiveNicotineIntervention named effectiveNicotineIntervention 0..*

Extension: CommonNicotineTrigger
Id: common-nicotine-trigger
Title: "Common Nicotine Trigger"
Description: "Common trigger for the patient to start using nicotine substances (like smoking a cigarette). For example, a patient's most common trigger might be that they feel an urge to smoke when they just woke up, or when they are waiting for something or someone."
* value[x] only CodeableConcept // TODO add String, and say in standard you can suggest additions to the code in the open source repo and use string meanwhile
* valueCodeableConcept from TriggerCode (preferred)

ValueSet: TriggerCode
* include codes from system TriggerCodeSystem

CodeSystem: TriggerCodeSystem
* #waiting "Waiting" "Waiting for something or someone"
// TODO

Extension: EffectiveNicotineIntervention
Id: effective-nicotine-intervention
Title: "Effective Nicotine Intervention"
Description: "Effective interventions for when the patient has an urge to use nicotine substances (for example wants to smoke a cigarette). An example would be if asking that the patient take a glass of water when they feel the urge to smoke has been effective for this patient."
* value[x] only CodeableConcept
* valueCodeableConcept from EffectiveInterventionCode (preferred)

ValueSet: EffectiveInterventionCode
* include codes from system EffectiveInterventionCodeSystem

CodeSystem: EffectiveInterventionCodeSystem
* #drink-water "Drink Water" "Taking a glass of water"
// TODO


Profile: SelfReportedNicotineDependance
Parent: Condition
Id: self-reported-nicotine-dependance
Title: "Self Reported Nicotine Dependance"
Description: "A condition of nicotine dependance (F17.2 ICD-10). The condition and its status updates are self-reported by the patient."
* bodySite 0..0
* verificationStatus 0..0 // we remove all options to "diagnosticize" here ,because these apps do not diagnise anyone. they treat a diagnosis made by a doctor
* recordedDate 0..0
* recorder 0..0
* asserter 0..0
* severity 0..0
* stage 0..0
* evidence 0..0 // important say in standard: we do not try to evaluate the clinical condition. we only record that F17.2 is being treated and what the patient self-reports
* clinicalStatus 0..0 // see above, point out in text that we do NOT evaluate clinical status, it is self reported (refer to the plan extension)
* note.text = "The status of this condition is only self-reported by the patient and is recorded in the patient's care plan."
* code.coding ^slicing.discriminator.type = #pattern
* code.coding ^slicing.discriminator.path = "coding"
* code.coding ^slicing.rules = #open
* code.coding contains icd10 1..1 MS
* code.coding[icd10] only http://fhir.de/StructureDefinition/CodingICD10GM  
* code.coding[icd10].code = http://fhir.de/CodeSystem/dimdi/icd-10-gm#F17.2 
