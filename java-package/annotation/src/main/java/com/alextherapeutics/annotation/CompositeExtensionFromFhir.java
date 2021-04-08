package com.alextherapeutics.annotation;

public @interface CompositeExtensionFromFhir {
    /**
     * The id of the extension. A 'fhir/StructureDefinition-{id}.json' must exist in the classpath.
     * @return
     */
    String id();

    /**
     * The common name of the extension in the parent resource
     * @return
     */
    String name();
    ExtensionFromFhir[] extensions();
}
