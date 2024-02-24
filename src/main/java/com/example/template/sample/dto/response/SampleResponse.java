package com.example.template.sample.dto.response;

import com.example.template.sample.domain.Sample;
import com.example.template.sample.domain.SampleItem;
import com.example.template.sample.enums.SampleStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;

@Getter
public class SampleResponse {
    private final String sampleName;
    private final SampleStatus sampleStatus;
    private final List<SampleItem> sampleItemList;

    @QueryProjection
    public SampleResponse(Sample sample) {
        this.sampleName = sample.getName();
        this.sampleStatus = sample.getSampleStatus();
        this.sampleItemList = sample.getSampleItemList();
    }
}
