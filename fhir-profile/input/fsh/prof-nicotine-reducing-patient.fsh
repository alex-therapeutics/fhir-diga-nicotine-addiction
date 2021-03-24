Profile: NicotineReducingPatient
Parent: Patient
* extension contains NicotineTrigger named commonNicotineTrigger 0..*
* extension contains EffectiveNicotineIntervention named effectiveNicotineIntervention 0..*

Instance: NicotineReducingPatientExample
InstanceOf: NicotineReducingPatient
* extension[NicotineTrigger][0].valueCodeableConcept = TriggerCodeSystem#waiting
* extension[EffectiveNicotineIntervention][0].valueCodeableConcept = EffectiveInterventionCodeSystem#drink-water
* active = true
* name.use = #nickname
* name.given = "Max"
* telecom.system = #email
* telecom.value = "max.mustermann@diga.de"
* gender = #male