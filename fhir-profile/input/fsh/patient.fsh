// Profile: NicotineReducingPatient
// Parent: Patient
// * 
// * name 1..* MS

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


// now I want to add something like "smokingStatus" on the Person resource.
// should this be a "Condition", which refers to clinical details, or an "Observation" extension, as it is self-reported?
// should it be a condition or a goal?

Profile: NicotineDependantCondition
Parent: Condition
* bodySite 0..0
* verificationStatus 0..0 // we remove all options to "diagnosticize" here ,because these apps do not diagnise anyone. they treat a diagnosis made by a doctor
* recordedDate 0..0
* recorder 0..0
* asserter 0..0
* severity 0..0
* stage 0..0
* evidence 0..0 // important say in standard: we do not try to evaluate the clinical condition. we only record that F17.2 is being treated and what the patient self-reports
// extension: selfReportedStatus for codeable concept same as "clinicalStatus"
// code: ICD-10 F17.2 use german basis extension here and point to that in standard


//

// mb we can do checkins as questionnaires but dont export any data on what tools were used, that doesnt have to be part of the data export.. too specific

