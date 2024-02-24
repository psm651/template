package com.example.template.sample.dto.param;

import com.example.template.sample.domain.Sample;
import com.example.template.sample.domain.SampleItem;
import com.example.template.sample.enums.SampleLegacy;
import com.example.template.sample.enums.SampleStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SampleFindDto {
    private String name;
    private SampleStatus sampleStatus;
    private SampleLegacy sampleLegacy;
    private List<SampleItem> sampleItemList;

    public SampleFindDto(Sample sample) {
        this.name = sample.getName();
        this.sampleStatus = sample.getSampleStatus();
        this.sampleLegacy = sample.getSampleLegacy();
        this.sampleItemList = sample.getSampleItemList();
    }
}
