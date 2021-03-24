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
* extension contains SelfReportedSmokingStatus named currentSelfReportedSmokingStatus 1..1
* extension[currentSelfReportedSmokingStatus] ^short = "The latest smoking status that was reported by the patient"

Instance: SelfReportedNicotineDependanceExample
InstanceOf: SelfReportedNicotineDependance
* extension[SelfReportedSmokingStatus].extension[status].valueCodeableConcept = SelfReportedStatusCodeSystem#actively-smoking
* extension[SelfReportedSmokingStatus].extension[reportedOn].valueDate = "2021-03-21"
* code.coding[icd10].code = http://fhir.de/CodeSystem/dimdi/icd-10-gm#F17.2 
* code.coding[icd10].version = "2020"
* subject = Reference(InlinePatient)

Instance: InlinePatient
InstanceOf: NicotineReducingPatient
Usage: #inline
