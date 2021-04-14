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

Extension: NicotineTrigger
Id: nicotine-trigger
Title: "Nicotine Trigger"
Description: "Trigger for the patient to start using nicotine substances (like smoking a cigarette). For example, a patient's most common trigger might be that they feel an urge to smoke when they just woke up, or when they are waiting for something or someone."
* value[x] only CodeableConcept
* valueCodeableConcept from TriggerCode (preferred)