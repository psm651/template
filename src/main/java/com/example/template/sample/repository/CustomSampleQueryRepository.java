package com.example.template.sample.repository;

import com.example.template.sample.dto.request.SampleFindRequest;
import com.example.template.sample.dto.response.SampleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomSampleQueryRepository {
    Page<SampleResponse> findAll(SampleFindRequest sampleFindRequest, Pageable pageable);

}
