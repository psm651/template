package com.example.template.module.sample.repository;

import com.example.template.module.sample.dto.request.SampleFindRequest;
import com.example.template.module.sample.dto.response.SampleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomSampleQueryRepository {
    Page<SampleResponse> findAll(SampleFindRequest sampleFindRequest, Pageable pageable);

}
