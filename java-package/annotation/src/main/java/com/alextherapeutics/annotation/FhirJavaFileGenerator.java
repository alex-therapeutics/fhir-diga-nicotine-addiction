package com.alextherapeutics.annotation;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.processing.Generated;
import javax.lang.model.element.Modifier;
import java.util.Date;

abstract class FhirJavaFileGenerator {
    protected static final String packageName = "com.alextherapeutics.model";

    abstract JavaFile toFile();

    protected TypeSpec.Builder addCommon(TypeSpec.Builder builder) {
                return builder.addAnnotation(
                AnnotationSpec.builder(Generated.class)
                        .addMember("value", "$S", this.getClass().getCanonicalName())
                        .addMember("date", "$S", new Date().toString())
                        .build()
        )
                .addAnnotation(Getter.class)
                .addAnnotation(AllArgsConstructor.class)
                .addModifiers(Modifier.PUBLIC);
    }
}
