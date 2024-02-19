package com.example.template.sample.enums;

import lombok.Getter;

@Getter
public enum SampleLegacy {
    MEANINGFUL_WORD("A", "legacy_word"),
    NONE("", "");

    private final String dbValue;
    private final String xmlValue;

    SampleLegacy(String dbValue, String xmlValue) {
        this.dbValue = dbValue;
        this.xmlValue = xmlValue;
    }
}
