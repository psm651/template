package com.example.template.sample.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleFindRequest {
    private String searchType;
    private String searchText;
}
