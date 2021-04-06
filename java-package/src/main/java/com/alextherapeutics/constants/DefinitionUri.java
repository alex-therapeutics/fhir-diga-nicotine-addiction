package com.alextherapeutics.constants;

/**
 * URIs for structure definitions in the profile set
 */
public class DefinitionUri {
    private static final String structure = "/StructureDefinition/";
    private static final String codeSystem = "/CodeSystem/";

    public static final String SELF_REPORTED_NICOTINE_USING_PATIENT = Canonical.root + structure + "self-reported-nicotine-using-patient";
    public static final String NICOTINE_TRIGGER = Canonical.root + structure + "nicotine-trigger";
    public static final String EFFECTIVE_INTERVENTION = Canonical.root + structure + "effective-nicotine-intervention";
    public final static String TRIGGER_CODE_SYSTEM = Canonical.root + codeSystem + "trigger-code-system";

}
