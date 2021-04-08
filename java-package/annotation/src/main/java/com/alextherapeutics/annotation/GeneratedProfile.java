package com.alextherapeutics.annotation;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.util.ElementUtil;
import com.squareup.javapoet.*;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hl7.fhir.r4.model.ElementDefinition;
import org.hl7.fhir.r4.model.StructureDefinition;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.tools.Diagnostic;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GeneratedProfile extends FhirJavaFileGenerator {
    private Element element;
    private StructureDefinition resource;
    private ExtensionFromFhir[] extensions;
    private Messager messager;
    private ProcessingEnvironment processingEnvironment;
    @Override
    JavaFile toFile() {
        var superClass = ClassName.get("org.hl7.fhir.r4.model", resource.getType());
        var profilePojoBuilder = addCommon(TypeSpec.classBuilder(element.getSimpleName() + "Profile"))
                .addAnnotation(
                        AnnotationSpec.builder(ResourceDef.class)
                                .addMember("name", "$S", resource.getType())
                                .addMember("profile", "$S", resource.getUrl())
                                .build()
                )
                .addAnnotation(Setter.class)
                .superclass(superClass);
        if (hasExtensions()) {
            profilePojoBuilder
                    .addFields(buildExtensionFields())
                    .addMethod(buildEmptyMethodForExtensions());
        }
        return JavaFile.builder(packageName, profilePojoBuilder.build()).build();
    }
    private List<FieldSpec> buildExtensionFields() {
        return Arrays.stream(extensions)
                .map(
                        extensionFromFhir -> {
                            var structureElement = resource
                                    .getDifferential()
                                    .getElement()
                                    .stream()
                                    .filter(Objects::nonNull)
                                    .filter(elementDefinition -> elementDefinition.getSliceName() != null)
                                    .filter(elementDefinition -> elementDefinition.getSliceName().equals(extensionFromFhir.name()))
                                    .findFirst();
                            if (structureElement.isEmpty()) {
                                messager.printMessage(Diagnostic.Kind.ERROR, String.format("Couldnt find extension with name %s in structure definition", extensionFromFhir.name()), element);
                                return null;
                            }
                            return buildExtensionField(extensionFromFhir, structureElement.get());
                        }
                ).collect(Collectors.toList());
    }
    private FieldSpec buildExtensionField(ExtensionFromFhir extension, ElementDefinition definition) {
        if (definition == null) {
            return null;
        }
        var min = definition.getMin();
        var max = definition.getMax() == null
                ? 1
                : definition.getMax().equals("*")
                ? Child.MAX_UNLIMITED
                : Integer.valueOf(definition.getMax());

        var type = ClassName.get(getType(extension));
        var list = ClassName.get("java.util", "List");
        var arrayList = ClassName.get("java.util", "ArrayList");
        var listOfType = ParameterizedTypeName.get(list, type);
        var finalType = max == 1 ? type : listOfType;
        var field = FieldSpec.builder(finalType, extension.name(), Modifier.PRIVATE)
                .addAnnotation(
                        AnnotationSpec.builder(Description.class)
                                .addMember("shortDefinition", "$S", definition.getShort())
                                .build()
                )
                .addAnnotation(
                        AnnotationSpec.builder(Extension.class)
                                .addMember("definedLocally", "$L", true)
                                .addMember("url", "$S", definition.getType().get(0).getProfile().get(0).asStringValue())
                                .addMember("isModifier", "$L", false)
                                .build()
                )
                .addAnnotation(
                        AnnotationSpec.builder(Child.class)
                                .addMember("name", "$S", extension.name())
                                .addMember("min", "$L", min)
                                .addMember("max", "$L", max)
                                .build()
                );
        if (max == 1) {
            field = field.initializer("new $T()", finalType);
        } else {
            field = field.initializer("new $T<>()", arrayList);
        }
        return field.build();
    }
    // https://stackoverflow.com/questions/7687829/java-6-annotation-processing-getting-a-class-from-an-annotation
    @SneakyThrows
    private Class getType(ExtensionFromFhir extension) {
        try {
            extension.valueType();
        } catch (MirroredTypeException e) {
            var utils = processingEnvironment.getTypeUtils();
            var type = (TypeElement) utils.asElement(e.getTypeMirror());
            return Class.forName(type.getQualifiedName().toString());
        }
        return null;
    }
    private MethodSpec buildEmptyMethodForExtensions() {
        var params = Arrays.stream(extensions)
                .map(ExtensionFromFhir::name)
                .map(CodeBlock::of)
                .collect(Collectors.toList());
        var paramsBlock = CodeBlock.join(params, ", ");

        return MethodSpec.methodBuilder("isEmpty")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(boolean.class)
                .addStatement("return super.isEmpty() && $T.isEmpty($L)", ElementUtil.class, paramsBlock)
                .build();
    }
    private boolean hasExtensions() {
        return extensions.length > 0;
    }
}
