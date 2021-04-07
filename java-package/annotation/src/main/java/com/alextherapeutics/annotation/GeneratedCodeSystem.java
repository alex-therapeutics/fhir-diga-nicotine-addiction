package com.alextherapeutics.annotation;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import lombok.AllArgsConstructor;
import org.hl7.fhir.r4.model.CodeSystem;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

@AllArgsConstructor
class GeneratedCodeSystem extends FhirJavaFileGenerator {
    private Element element;
    private CodeSystem resource;

    @Override
    JavaFile toFile() {
        var codeEnumBuilder = addCommon(TypeSpec.enumBuilder(element.getSimpleName() + "Code"))
                .addField(String.class, "code", Modifier.PRIVATE)
                .addField(String.class, "display", Modifier.PRIVATE);
        for (var concept : resource.getConcept()) {
            codeEnumBuilder.addEnumConstant(
                    concept.getCode(),
                    TypeSpec.anonymousClassBuilder(
                            "$S, $S",
                            concept.getDisplay(),
                            concept.getDefinition()
                    ).build()
            );
        }
        return JavaFile.builder(packageName, codeEnumBuilder.build())
                .build();
    }
}
