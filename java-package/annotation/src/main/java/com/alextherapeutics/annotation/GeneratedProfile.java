package com.alextherapeutics.annotation;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StructureDefinition;

import javax.lang.model.element.Element;

@AllArgsConstructor
public class GeneratedProfile extends FhirJavaFileGenerator {
    private Element element;
    private StructureDefinition resource;
    @Override
    JavaFile toFile() {
        var profilePojoBuilder = addCommon(TypeSpec.classBuilder(element.getSimpleName() + "Profile"))
                .addAnnotation(
                        AnnotationSpec.builder(ResourceDef.class)
                                .addMember("name", "$S", resource.getType())
                                .addMember("profile", "$S", resource.getUrl())
                                .build()
                )
                .addAnnotation(Setter.class)
                .superclass(Patient.class);
        return JavaFile.builder(packageName, profilePojoBuilder.build()).build();
    }
}
