Alias: ICD-DE = http://fhir.de/StructureDefinition/CodingICD10GM
// Alias: ICD-DE = http://fhir.de/StructureDefinition/icd-10-gm-stern
// Alias: ICD-DE = http://fhir.de/CodeSystem/dimdi/icd-10-gm
// - it looks like we dont need to make a profile on "Patient" which changes anything. Maybe we should make an extension and add to it though, non-mandatory diga code?


// Wrapper resource for all the data. Personal details SHALL NOT be included: if you have this information, it should be provided in the linked Patient resource
Profile: NicotineReducingPerson
Parent: Person
* name 0..1 // TODO SLICE THIS TO ONLY CONTAIN "use: nickname", see https://www.hl7.org/fhir/datatypes.html#HumanName, and specify in standard that this field is for "usernames", and real person info is in "patient"
            // that also means that we need to say that patient->name cannot include "nickname" (?)
* gender 0..0
* birthDate 0..0
* address 0..0
* photo 0..0
* telecom 0..0
// * link 1..1 // -- only to a "patient" reference

// * id etc.. https://simplifier.net/basisprofil-de-r4/identifierpid for german id profile



Profile: NicotineDependanceCondition
Parent: Condition
Id: nicotine-dependance-condition
Title: "Nicotine Dependance Condition"
Description: "A condition of nicotine dependance (F17.2 ICD-10) which is being treated. The condition and its status updates are self-reported by the patient."
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
