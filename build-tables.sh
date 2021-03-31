#!/bin/bash

Q_RESP=nicotine-treatment-questionnaire-response
Q=nicotine-treatment-questionnaire

declare -a resources=($Q $Q_RESP)

for i in "${resources[@]}"
do
    ./build-table-from-output.js -o ./document/$i.tex -f ./fhir-profile/output/StructureDefinition-$i.json
done

# ./build-table-from-output.js -o ./document/nicotine-treatment-questionnaire.tex -f ./fhir-profile/output/StructureDefinition-nicotine-treatment-questionnaire.json
# ./build-table-from-output.js -o ./document/$Q.tex -f ./fhir-profile/output/StructureDefinition-$Q.json
