package com.alextherapeutics.annotation;

public @interface ExtensionFromFhir {
    String name();
    Class<?> valueType();
}
