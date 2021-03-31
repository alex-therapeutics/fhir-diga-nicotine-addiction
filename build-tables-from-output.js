#!/usr/bin/env node
const fs = require('fs')

// This converts one structure definition into a latex booktabs table
const target = './document/nicotine-treatment-questionnaire-response.tex'

const file = './fhir-profile/output/StructureDefinition-nicotine-treatment-questionnaire-response.json'
const json = JSON.parse(fs.readFileSync(file))

const igFile = './fhir-profile/fsh-generated/resources/ImplementationGuide-com.alextherapeutics.fhir.nicotine.json'
const igJson = JSON.parse(fs.readFileSync(igFile))

const uri = json.url
const name = json.name
const type = json.type


const bookTabsHeader = 
`
\\begin{table}[]
\\begin{tabular}{@{}lll@{}}
\\toprule
\\multicolumn{1}{c}{Name}               & \\multicolumn{1}{c}{card.} & \\multicolumn{1}{c}{Type}       \\\\ \\midrule
`

const structureHeader = `
\\textbf{${name}} & \\textbf{-}                & \\textbf{${type}} \\\\ \\midrule
`

const jsonDifferential = json.differential.element
jsonDifferential.shift() // first element is the parent
const jsonSnapshot = json.snapshot.element

const tabRows = jsonDifferential.map(item => buildTabRow(item, jsonSnapshot))
const elements = tabRows.join('\n').concat('\\bottomrule')
const bookTabsEnd = `
\\end{tabular}
\\caption{The differential for the ${name} profile when compared to the base ${type} resource.}
\\label{tab:${name}}
\\end{table}
`
const result = bookTabsHeader + structureHeader + elements + bookTabsEnd

fs.writeFileSync(target, result)

console.info('Done!')

function buildTabRow(item, snapshots) {
    const rowName = item.id
    const cardMin = item.min || snapshots.find(el => el.id === item.id).min
    const cardMax = item.max || snapshots.find(el => el.id === item.id).max
    const rowType = getType(item, snapshots)
    return `${rowName} & ${cardMin}..${cardMax} & ${rowType} \\\\`
}

function getType(item, snapshots) {
    if (item.binding) {

    }
    const types = item.type || snapshots.find(el => el.id === item.id).type
    if (types.length === 1) {
        return buildTypeString(types[0], snapshots)
    }
    return type.code
}

function buildTypeString(type, snapshots) {
    const targetProfileId = findIdFromCanonical(type.targetProfile[0])
    if (!targetProfileId) {
        return type.code
    }
    const name = findProfileNameFromIg(igJson, targetProfileId)
    return `${type.code}(${name})`
}

function findProfileNameFromIg(ig, targetId) {
    if (!targetId.includes('-')) return targetId
    const def = igJson.definition.resource.find(el => el.reference.reference.includes(targetId))
    if (def.exampleCanonical) {
        return findProfileNameFromIg(findIdFromCanonical(def.exampleCanonical))
    }
    return def.name.replace(/\s/g, '')
}

function findIdFromCanonical(canonical) {
    const split = canonical.split('/')
    return split[split.length - 1]
}

 // TODO make binding and then script starter which runs these for all profiles
 // also make one for extensions..