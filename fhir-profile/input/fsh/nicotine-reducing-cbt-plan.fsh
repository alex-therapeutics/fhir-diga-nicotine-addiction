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
* addresses[selfReportedNicotineDependance] only Reference(SelfReportedNicotineDependance)

Extension: SelfReportedSmokingStatus
Id: self-reported-smoking-status
Title: "Self Reported Smoking Status"
Description: "A report on the smoking status made by the patient him/herself at a point in time. Used for example when a digital application asks the patient if they are smoke free, or when a patient reports that they have relapsed after being smoke free."
* extension contains
    reportedOn 1..1 and
    status 1..1
* extension[reportedOn] ^short = "The date this report was made"
* extension[reportedOn].value[x] only date
* extension[reportedOn].valueDate 1..1
* extension[status] ^short = "The status that was reported"
* extension[status].value[x] only Coding
* extension[status].valueCoding from SelfReportedSmokingStatusCode

ValueSet: SelfReportedSmokingStatusCode
Id: self-reported-smoking-status-code
Title: "Self Reported Smoking Status Code"
Description: "A self-reported smoking status"
* include codes from system SelfReportedStatusCodeSystem

CodeSystem: SelfReportedStatusCodeSystem
Id: self-reported-status-code-system
Title: "Self Reported Smoking Status Code System"
Description: "Available values for self-reporting smoking status"
* #actively-smoking "Actively Smoking" "Currently actively smoking and hasn't attempted to cut down or quit yet, or has relapsed and started to smoke again."
* #cutting-down "Cutting Down" "Started a process of progressively cutting down on the amount of cigarettes smoked"
* #quit-smoking "Quit Smoking" "Has quit smoking."
