package com.alextherapeutics;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.ResultSeverityEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PatientDataExporterTest {
    private static PatientDataExporter exporter;

    @BeforeAll
    static void first() {
        exporter = new PatientDataExporter();
    }
    @Test
    void testExportsValidBundle() {
        var validator = ProfileSpecificValidatorFactory.getValidator(FhirContext.forR4());
        var export = exporter.getXmlExport(ExamplePatientTreatmentData.get());
        var res = validator.validateWithResult(export);
        var errors = res.getMessages().stream().filter(message -> message.getSeverity().equals(ResultSeverityEnum.ERROR)).collect(Collectors.toList());
        var errorsNotContainingSlicingEvaluation = errors.stream().filter(error -> !error.getMessage().contains("Slicing cannot be evaluated:")).collect(Collectors.toList());
        assertTrue(errorsNotContainingSlicingEvaluation.size() == 0);
    }

}