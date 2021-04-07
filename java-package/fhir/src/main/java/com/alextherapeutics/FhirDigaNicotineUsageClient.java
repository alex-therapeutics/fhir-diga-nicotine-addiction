package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;

public class FhirDigaNicotineUsageClient {
    private FhirContext fhirContext;

    public FhirDigaNicotineUsageClient() {
        fhirContext = FhirContext.forR4();
    }
}
