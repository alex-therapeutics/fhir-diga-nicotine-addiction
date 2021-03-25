Profile: ExportedNicotineUsageTreatmentData
Id: exported-nicotine-usage-treatment-data
Title: "Exported Nicotine Usage Treatment Data"
Description: "A bulk export containing all data pertaining to a single patient being treated for nicotine usage"
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
* section[patientData] ^short = "Data on the self-reported nicotine-using patient"
* section[patientData].title = "Self-reported nicotine-using patientdata"
* section[patientData].entry 1..1 MS
* section[patientData].entry only Reference(SelfReportedNicotineUsingPatient or Patient) 
* section contains selfReportedCondition 1..1 MS
* section[selfReportedCondition] ^short = "Data on the self-reported condition of nicotine usage"
* section[selfReportedCondition].title = "Self-reported nicotine-usage condition"
* section[selfReportedCondition].entry 1..1 MS
* section[selfReportedCondition].entry only Reference(SelfReportedNicotineUsage or Condition)
* section contains questionnaires 1..1 MS
* section[questionnaires] ^short = "Definitions for questionnaires during the nicotine usage treatment"
* section[questionnaires].title = "Questionnaire definitions"
* section[questionnaires].entry only Reference(NicotineTreatmentQuestionnaire or Questionnaire)
* section contains questionnaireResponses 1..1 MS
* section[questionnaireResponses] ^short = "Questionnaire responses by the patient during the nicotine treatment"
* section[questionnaireResponses].title = "Questionnaire responses"
* section[questionnaireResponses].entry only Reference(NicotineTreatmentQuestionnaireResponse or QuestionnaireResponse)
