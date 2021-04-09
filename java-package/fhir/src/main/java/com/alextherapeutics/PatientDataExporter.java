package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;
import com.alextherapeutics.model.PatientTreatmentData;

import java.io.IOException;
import java.io.Writer;

/**
 * Main package class for easily exporting a patient's DiGA treatment data.
 * Provides methods for getting the exports as JSON or XML strings, or writing them to a {@link Writer}.
 */
public class PatientDataExporter {
    private FhirContext fhirContext;
    private PatientTreatmentDataBundleFactory bundleFactory;

    public PatientDataExporter() {
        fhirContext = FhirContext.forR4();
        bundleFactory = new PatientTreatmentDataBundleFactory(fhirContext);
    }

    /**
     * Get a patient's data export as an XML String
     * @param patientData
     * @return
     */
    public String getXmlExport(PatientTreatmentData patientData) {
        var bundle = bundleFactory.createBundle(patientData);
        return fhirContext.newXmlParser().setPrettyPrint(true).encodeResourceToString(bundle);
    }

    /**
     * Write a patient's data export as XML to a {@link Writer}
     * @param data
     * @param writer
     */
    public void writeXmlExport(PatientTreatmentData data, Writer writer) throws IOException {
        fhirContext.newXmlParser().encodeResourceToWriter(bundleFactory.createBundle(data), writer);
    }

    /**
     * Get a patient's data export as a JSON String
     * @param patientData
     * @return
     */
    public String getJsonExport(PatientTreatmentData patientData) {
        var bundle = bundleFactory.createBundle(patientData);
        return fhirContext.newJsonParser().encodeResourceToString(bundle);
    }

    /**
     * Write a patient's data export as JSON to a {@link Writer}
     * @param data
     * @param writer
     */
    public void writeJsonExport(PatientTreatmentData data, Writer writer) throws IOException {
        fhirContext.newJsonParser().encodeResourceToWriter(bundleFactory.createBundle(data), writer);
    }

}
