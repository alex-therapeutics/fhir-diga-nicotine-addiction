package com.alextherapeutics.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface GeneratedFromFhir {
    String id();
    FhirType type() default FhirType.PROFILE;
    ExtensionFromFhir[] extensions() default {};
}