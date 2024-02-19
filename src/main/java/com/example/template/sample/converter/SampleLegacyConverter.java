package com.example.template.sample.converter;

import com.example.template.sample.enums.SampleLegacy;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

import static com.example.template.sample.enums.SampleLegacy.NONE;
import static com.example.template.sample.enums.SampleLegacy.values;

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
        return Arrays.stream(values())
                .filter(v -> v.getDbValue().equals(dbData))
                .findAny()
                .orElse(NONE);
    }
}
