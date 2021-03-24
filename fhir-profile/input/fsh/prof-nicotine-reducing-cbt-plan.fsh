Profile: NicotineReducingCBTPlan
Id: nicotine-reducing-cbt-plan
Title: "Nicotine Reducing CBT Plan"
Description: "A Care Plan for reducing nicotine dependance using CBT"
Parent: CarePlan
* extension contains SelfReportedSmokingStatus named selfReportedSmokingStatus 0..*
* intent = #plan
* description 1..1 // in standard we should explain that this is the primary point of finding information for how the app works
                    // f.e in a patient journal, this is what would explain what this app's treatment does
* created 1..1 // when it was first created. for eila this is when starting to use app.
* addresses ^slicing.discriminator.type = #profile
* addresses ^slicing.discriminator.path = "addresses"
* addresses ^slicing.rules = #open
* addresses contains selfReportedNicotineDependance 1..1 MS
* addresses[selfReportedNicotineDependance] only Reference(SelfReportedNicotineDependance or Condition)
* subject only Reference(NicotineReducingPatient or Patient)

Instance: NicotineReducingCBTPlanExample
InstanceOf: NicotineReducingCBTPlan
* extension[SelfReportedSmokingStatus][0].extension[reportedOn].valueDate = "2021-01-01"
* extension[SelfReportedSmokingStatus][0].extension[status].valueCodeableConcept = SelfReportedStatusCodeSystem#actively-smoking
* extension[SelfReportedSmokingStatus][1].extension[reportedOn].valueDate = "2021-02-01"
* extension[SelfReportedSmokingStatus][1].extension[status].valueCodeableConcept = SelfReportedStatusCodeSystem#cutting-down
* extension[SelfReportedSmokingStatus][2].extension[reportedOn].valueDate = "2021-03-01"
* extension[SelfReportedSmokingStatus][2].extension[status].valueCodeableConcept = SelfReportedStatusCodeSystem#quit-smoking
* created = "2021-01-01"
* addresses[selfReportedNicotineDependance] = Reference(InlineCondition)
* status = #active
* intent = #plan
* subject = Reference(InlinePatient)
* description = 
    """
    A treatment plan using the Eila DiGA.
    This is a CBT-based treatment plan where the patient progress towards quitting smoking by cutting down on cigarettes for a period of time.
    The patient has activities like logging their cigarettes, their triggers, ... etc
    TODO
    """

Instance: InlineCondition
InstanceOf: SelfReportedNicotineDependance
Usage: #inline
* extension[SelfReportedSmokingStatus].extension[status].valueCodeableConcept = SelfReportedStatusCodeSystem#quit-smoking
* extension[SelfReportedSmokingStatus].extension[reportedOn].valueDate = "2021-03-01"
* subject = Reference(InlinePatient)

Instance: InlinePatient
InstanceOf: NicotineReducingPatient
Usage: #inline