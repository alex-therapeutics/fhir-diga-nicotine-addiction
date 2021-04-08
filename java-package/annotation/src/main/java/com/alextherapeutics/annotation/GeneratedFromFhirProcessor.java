package com.alextherapeutics.annotation;

import ca.uhn.fhir.context.FhirContext;
import com.google.auto.service.AutoService;
import org.hl7.fhir.r4.model.StructureDefinition;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

@AutoService(Processor.class)
public class GeneratedFromFhirProcessor extends AbstractProcessor {
    private static final String packageName = "com.alextherapeutics.model";
    private Messager messager;
    private Filer filer;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
    }
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        var annotations = new LinkedHashSet<String>();
        annotations.add(GeneratedFromFhir.class.getCanonicalName());
        return annotations;
    }
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        var elements = roundEnv.getElementsAnnotatedWith(GeneratedFromFhir.class);
        for (var element: elements) {
            if (element.getKind() != ElementKind.INTERFACE) {
                error(element, "The @%s annotation only works for interfaces");
                return true;
            }
            var fhirType = element.getAnnotation(GeneratedFromFhir.class).type();
            var fhirId = element.getAnnotation(GeneratedFromFhir.class).id();
            var extensions = element.getAnnotation(GeneratedFromFhir.class).extensions();
            Path path;
            if (fhirType == FhirType.CODE_SYSTEM) {
                path = Paths.get("fhir", "CodeSystem-" + fhirId + ".json");
            } else {
                path = Paths.get("fhir", "StructureDefinition-" + fhirId + ".json");
            }
            var file = GeneratedFromFhirProcessor.class.getClassLoader().getResourceAsStream(path.toString());
            var ctxt = FhirContext.forR4();
            var resource = ctxt.newJsonParser().parseResource(file);
            FhirJavaFileGenerator fileGenerator;
            if (fhirType == FhirType.CODE_SYSTEM) {
                fileGenerator = new GeneratedCodeSystem(element, (org.hl7.fhir.r4.model.CodeSystem) resource);
            } else {
                fileGenerator = new GeneratedProfile(element, (StructureDefinition) resource, extensions, messager, processingEnv);
            }
            try {
                fileGenerator.toFile().writeTo(filer);
            } catch (IOException e) {
                error(element, e.getMessage());
            }
        }
        return true;
    }


    private void error(Element e, String msg, Object ...args) {
        messager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e
        );
    }
}
