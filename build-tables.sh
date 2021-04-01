#!/bin/bash

Q_RESP=nicotine-treatment-questionnaire-response
Q=nicotine-treatment-questionnaire
EXP=exported-nicotine-usage-treatment-data
PLAN=nicotine-usage-treatment-plan
COND=self-reported-nicotine-usage
PATIENT=self-reported-nicotine-using-patient
NIC=nicotine-trigger
INT=effective-nicotine-intervention
STATUS=self-reported-smoking-status

declare -a resources=($Q $Q_RESP $EXP $PLAN $COND $PATIENT $NIC $INT $STATUS)

for i in "${resources[@]}"
do
    ./build-table-from-output.js -o ./document/$i.tex -f ./fhir-profile/output/StructureDefinition-$i.json
done

echo "ALL done!"
