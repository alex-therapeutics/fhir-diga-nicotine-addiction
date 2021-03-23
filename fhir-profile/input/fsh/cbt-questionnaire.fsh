Profile: NicotineCbtQuestionnaireResponse
Id: nicotine-cbt-questionnaire-response
Title: "Nicotine CBT Questionnaire Response"
Description: "A questionnaire response dealing with nicotine as part of a CBT based treatment program"
Parent: QuestionnaireResponse
* questionnaire 1..1 // responses SHOULD link to a defined questionnaire, always, so the other party can understand what the standard forms are. and a practicioner can take a look at what the DiGA does
* source 1..1 
* source only Reference(NicotineReducingPatient) // SHOULD be the patient
* item.answer.valueCoding from TriggerCode (example) // indicate in standard that these codes can be used for logging or other questions involved 'triggers' which is a CBT concept
// kanske ta bort

Profile: NicotineCbtQuestionnaire
Id: nicotine-cbt-questionnaire
Title: "Nicotine CBT Questionnaire"
Description: "A questionnaire belonging to a nicotine reducing care program."
Parent: Questionnaire
* title 1..1
* description 1..1 // explain that this is the primary spot to look for "what was asked" for journals
* purpose 1..1 // a manufacturer should use these fields to explain in a humnan-friendly way what this questionnaire is for and what it does