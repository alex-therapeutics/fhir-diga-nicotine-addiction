Profile: ExportedNicotineCbtData
Id: exported-nicotine-cbt-data
Title: "Exported Nicotine CBT Data"
Description: "A bulk export containing all data pertaining to one nicotine reducing patient."
Parent: Composition
* type.text = "DiGA Data Export"
* subject 1..1 
* subject only Reference(NicotineReducingPatient or Patient)
* status = #final
* title 1..1
* section ^slicing.discriminator.type = #value
* section ^slicing.discriminator.path = "section"
* section ^slicing.rules = #closed
* section contains patientData 1..1 MS
* section[patientData].title = "Data for a self-reporting nicotine reducing patient"
* section[patientData].entry 1..1 MS
* section[patientData].entry only Reference(NicotineReducingPatient or Patient) 
* section contains selfReportedCondition 1..1 MS
* section[selfReportedCondition].title = "Data on the self-reported condition"
* section[selfReportedCondition].entry 1..1 MS
* section[selfReportedCondition].entry only Reference(SelfReportedNicotineDependance or Condition)
* section contains questionnaires 1..1 MS
* section[questionnaires].title = "Questionnaire definitions for the CBT treatment"
* section[questionnaires].entry only Reference(NicotineCbtQuestionnaire or Questionnaire)
* section contains questionnaireResponses 1..1 MS
* section[questionnaireResponses].title = "Questionnaire responses during the CBT treatment"
* section[questionnaireResponses].entry only Reference(NicotineCbtQuestionnaireResponse or QuestionnaireResponse)
