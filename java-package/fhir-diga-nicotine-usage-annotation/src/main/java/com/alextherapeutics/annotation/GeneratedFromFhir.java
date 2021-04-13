package com.alextherapeutics.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate that this field should generate a class from a FHIR structure definition located in resources/fhir/
 * Fields annotated with this will generate a class with the same name as the field.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface GeneratedFromFhir {
    /**
     * The FHIR resource ID. This must correspond to the filename of the FHIR resource, for example:
     * StructureDefinition-exported-nicotine-usage-treatment-data.json => id = exported-nicotine-usage-treatment-data
     * @return
     */
    String id();

    /**
     * The type of resource this generates.
     * @return
     */
    FhirType type() default FhirType.PROFILE;

    /**
     * Extension definitions contained in this resource. Unfortunately it is not possible to generate these from the
     * FHIR files in all cases, so they are defined separately here.
     * @return
     */
    ExtensionFromFhir[] extensions() default {};

    /**
     * Composite extension definitions defined in this resource.
     * @return
     */
    CompositeExtensionFromFhir[] compositeExtensions() default {};
}
