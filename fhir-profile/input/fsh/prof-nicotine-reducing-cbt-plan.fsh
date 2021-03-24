Profile: NicotineReducingCBTPlan
Id: nicotine-reducing-cbt-plan
Title: "Nicotine Reducing CBT Plan"
Description: "A Care Plan for reducing nicotine dependance using CBT"
Parent: CarePlan
* extension contains SelfReportedSmokingStatus named selfReportedSmokingStatus 0..*
* description 1..1 // in standard we should explain that this is the primary point of finding information for how the app works
                    // f.e in a patient journal, this is what would explain what this app's treatment does
* created 1..1 // when it was first created. for eila this is when starting to use app.
* author 1..1 // extend/slice to only be "organization" reference. this should refer to the diga manufacturer
* addresses ^slicing.discriminator.type = #profile
* addresses ^slicing.discriminator.path = "addresses"
* addresses ^slicing.rules = #open
* addresses contains selfReportedNicotineDependance 1..1 MS
* addresses[selfReportedNicotineDependance] only Reference(SelfReportedNicotineDependance or Condition)
