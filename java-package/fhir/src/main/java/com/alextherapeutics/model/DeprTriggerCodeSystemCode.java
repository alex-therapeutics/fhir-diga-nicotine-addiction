package com.alextherapeutics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeprTriggerCodeSystemCode {
    WAITING("waiting", "Waiting");

    private String code;
    private String display;
    // TODO change to interface and generate this enum from the json files
    // !!! ta bort effective intervention code system från sushi!

}
