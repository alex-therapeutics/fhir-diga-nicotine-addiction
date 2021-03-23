Profile: NicotineCbtQuestionnaireResponse
Id: nicotine-cbt-questionnaire-response
Title: "Nicotine CBT Questionnaire Response"
Description: "A questionnaire response dealing with nicotine as part of a CBT based treatment program"
Parent: QuestionnaireResponse
* questionnaire 1..1 // responses SHOULD link to a defined questionnaire, always, so the other party can understand what the standard forms are. and a practicioner can take a look at what the DiGA does
* source 1..1 
* source only Reference(NicotineReducingPatient) // SHOULD be the patient
// * item.answer.value[x] ^slicing.discriminator.type = #profile
// * item.answer.value[x] ^slicing.discriminator.path = "value[x]"
// * item.answer.value[x] ^slicing.rules = #open
// * item.answer.value[x] contains valueTriggerCode 0..1
// * item.answer.value[x][valueTriggerCode] only Coding // add option for the answer to be a trigger. implementors of standard can use the defined codes, OR propose changes in the open source standard to add their own, and use valueString in the meantime
// * item.answer.value[x][valueTriggerCode] from TriggerCode
// * item.answer.value.extension contains CommonNicotineTrigger named tr 0..1
// * item.answer.valueCoding from TriggerCode

Profile: NicotineCbtQuestionnaire
Id: nicotine-cbt-questionnaire
Title: "Nicotine CBT Questionnaire"
Description: "A questionnaire belonging to a nicotine reducing care program."
Parent: Questionnaire
* title 1..1
* description 1..1 // explain that this is the primary spot to look for "what was asked" for journals
* purpose 1..1 // a manufacturer should use these fields to explain in a humnan-friendly way what this questionnaire is for and what it does