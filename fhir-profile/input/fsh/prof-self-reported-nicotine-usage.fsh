/*
 * Copyright 2021-2021 Alex Therapeutics AB and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

Profile: SelfReportedNicotineUsage
Parent: Condition
Id: self-reported-nicotine-usage
Title: "Self Reported Nicotine Usage"
Description: "A condition of nicotine usage (F17.2 ICD-10). The condition and its status updates are self-reported by the patient."
* bodySite 0..0
* verificationStatus 0..0 // we remove all options to "diagnosticize" here ,because these apps do not diagnise anyone. they treat a diagnosis made by a doctor
* recordedDate 0..0
* recorder 0..0
* asserter 0..0
* severity 0..0
* stage 0..0
* evidence 0..0 // important say in standard: we do not try to evaluate the clinical condition. we only record that F17.2 is being treated and what the patient self-reports
* clinicalStatus 0..0 // see above, point out in text that we do NOT evaluate clinical status, it is self reported (refer to the plan extension)
* subject only Reference(SelfReportedNicotineUsingPatient or Patient)
* code 1..1
* code.coding ^slicing.discriminator.type = #pattern
* code.coding ^slicing.discriminator.path = "coding"
* code.coding ^slicing.rules = #open
* code.coding contains icd10 1..1 MS
* code.coding[icd10] ^short = "The ICD-10 coding of the condition"
* code.coding[icd10] only http://fhir.de/StructureDefinition/CodingICD10GM
* code.coding[icd10].code = http://fhir.de/CodeSystem/dimdi/icd-10-gm#F17.2
* code.coding[icd10].version = "2021"
* extension contains SelfReportedSmokingStatus named currentSelfReportedSmokingStatus 1..1
* extension[currentSelfReportedSmokingStatus] ^short = "The latest smoking status that was reported by the patient"

Instance: SelfReportedNicotineUsageExample
Description: "An example of a self reported nicotine usage condition. The current (last reported, on 2021-03-21) status is that the patient is actively smoking"
InstanceOf: SelfReportedNicotineUsage
* extension[SelfReportedSmokingStatus].extension[status].valueCodeableConcept = SelfReportedStatusCodeSystem#actively_smoking
* extension[SelfReportedSmokingStatus].extension[reportedOn].valueDate = "2021-03-21"
* code.coding[icd10].code = http://fhir.de/CodeSystem/dimdi/icd-10-gm#F17.2 
* code.coding[icd10].version = "2021"
* subject = Reference(SelfReportedNicotineUsingPatientExample)
