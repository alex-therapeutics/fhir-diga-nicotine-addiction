package com.alextherapeutics.annotation;

/**
 * Defines a composite extension contained in a resource. Composite extensions are extensions that contain
 * several fields, not just a value. For example, the "selfReportedSmokingStatus" extension has two fields, "status" and "reportedOn".
 */
public @interface CompositeExtensionFromFhir {
    /**
     * The id of the extension. A 'fhir/StructureDefinition-{id}.json' must exist in the classpath.
     * @return
     */
    String id();

    /**
     * The common name of the extension in the parent resource. For example, if you had a composite extension and you name it
     * "myCompositeExtension", that should be this value.
     * @return
     */
    String name();

    /**
     * Definitions for the fields contained in the composite extension.
     * @return
     */
    ExtensionFromFhir[] extensions();
}
