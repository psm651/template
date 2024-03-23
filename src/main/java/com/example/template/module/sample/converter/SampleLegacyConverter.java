package com.example.template.module.sample.converter;

import com.example.template.module.sample.enums.SampleLegacy;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class SampleLegacyConverter implements AttributeConverter<SampleLegacy, String> {
    @Override
    public String convertToDatabaseColumn(SampleLegacy attribute) {
        if (attribute == null) {
            return "";
        }
        return attribute.getDbValue();
    }

    @Override
    public SampleLegacy convertToEntityAttribute(String dbData) {
        return Arrays.stream(SampleLegacy.values())
                .filter(v -> v.getDbValue().equals(dbData))
                .findAny()
                .orElse(SampleLegacy.NONE);
    }
}
