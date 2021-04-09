package com.alextherapeutics.annotation;

/**
 * A definition of an extension contained in a resource.
 */
public @interface ExtensionFromFhir {
    /**
     * The name of the extension. This must be the name of the slice on 'extension' on the parent resource.
     * For example, if you defined an extension called "effectiveNicotineIntervention", then that should be the name.
     * @return
     */
    String name();

    /**
     * The HAPI type of the extension type. For example, if your extension is of the value type {@link org.hl7.fhir.r4.model.StringType},
     * then that class should be this value. This is the reason we cannot generate the extensions from the parent profile definition - the
     * extension value type is not present there.
     * @return
     */
    Class<?> valueType();
}
