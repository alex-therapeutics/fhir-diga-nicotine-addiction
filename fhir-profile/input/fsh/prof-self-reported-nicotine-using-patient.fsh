Profile: SelfReportedNicotineUsingPatient
Id: self-reported-nicotine-using-patient
Description: "A patient who has reported they are using nicotine and are being treated for it."
Parent: Patient
* extension contains NicotineTrigger named commonNicotineTrigger 0..*
* extension contains EffectiveNicotineIntervention named effectiveNicotineIntervention 0..*

Instance: SelfReportedNicotineUsingPatientExample
InstanceOf: SelfReportedNicotineUsingPatient
* extension[NicotineTrigger][0].valueCodeableConcept = TriggerCodeSystem#waiting
* extension[EffectiveNicotineIntervention][0].valueCodeableConcept = EffectiveInterventionCodeSystem#drink-water
* active = true
* name.use = #nickname
* name.given = "Max"
* telecom.system = #email
* telecom.value = "max.mustermann@diga.de"
* gender = #male