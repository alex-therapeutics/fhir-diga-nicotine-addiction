Profile: ExportedNicotineUsageTreatmentData
Id: exported-nicotine-usage-treatment-data
Title: "Exported Nicotine Usage Treatment Data"
Description: "A bulk export containing all data pertaining to one patient being reated for nicotine usage"
Parent: Composition
* type.text = "DiGA Data Export"
* subject 1..1 
* subject only Reference(SelfReportedNicotineUsingPatient or Patient)
* status = #final
* title 1..1
* section ^slicing.discriminator.type = #value
* section ^slicing.discriminator.path = "section"
* section ^slicing.rules = #closed
* section contains patientData 1..1 MS
* section[patientData].title = "Data for a self-reporting nicotine using patient"
* section[patientData].entry 1..1 MS
* section[patientData].entry only Reference(SelfReportedNicotineUsingPatient or Patient) 
* section contains selfReportedCondition 1..1 MS
* section[selfReportedCondition].title = "Data on the self-reported condition"
* section[selfReportedCondition].entry 1..1 MS
* section[selfReportedCondition].entry only Reference(SelfReportedNicotineUsage or Condition)
* section contains questionnaires 1..1 MS
* section[questionnaires].title = "Questionnaire definitions for the nicotine treatment"
* section[questionnaires].entry only Reference(NicotineTreatmentQuestionnaire or Questionnaire)
* section contains questionnaireResponses 1..1 MS
* section[questionnaireResponses].title = "Questionnaire responses during the nicotine treatment"
* section[questionnaireResponses].entry only Reference(NicotineTreatmentQuestionnaireResponse or QuestionnaireResponse)
