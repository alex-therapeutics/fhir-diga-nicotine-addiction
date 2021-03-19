# FHIR Profile & Standard for DiGAs that treat nicotine addiction using CBT

This repository contains sources for
a) the FHIR profile that defines how data should be structured (`fhir-profile`)
b) the written Standard (`document`)

## Releases
Releases contain the Standard in PDF format and the FHIR Profile artifacts. (todo)

## Contributions
The standard with the FHIR profiles are actively maintained by the team at Alex Therapeutics. However, contributions are welcome from other actors who wish to use the standard. For example, if you have certain extensions you need to make for your use-case, we will happily accept PRs which modify the profiles and add to the text in the Standard, and then coordinate a new release of the Standard. You probably want to open an Issue first on this repository so that we can discuss the change. Note that any changes that include inserting new mandatory attributes (`1..1`) will likely be rejected, as that constitutes a breaking change for all implementers. However, any optional extensions or additions to value code sets can be added to the Standard without too much difficulty.

## Standard Build Instructions
The Standard PDF is written and generated using LaTEX.

## FHIR Build Instructions
The FHIR profiles are generated using FHIR Shorthand (link), which is a language for writing FHIR profiles and implementation guides. You can compile the profile using _sushi_ and the `*.sh*` scripts (`_genonce.sh` generates the IG if you are properly set up, see Sushi instructions)

## License
The source code contained in this repository is licensed under the Apache 2.0 license, with copyright by Alex Therapeutics AB.

The build artifacts - the Standard and the FHIR Artifacts contained in a _Release_ - are licensed under the Creative Commons Attribution 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/ or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
