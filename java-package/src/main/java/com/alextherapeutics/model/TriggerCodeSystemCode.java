package com.alextherapeutics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TriggerCodeSystemCode {
    WAITING("waiting", "Waiting");

    private String code;
    private String display;

}
