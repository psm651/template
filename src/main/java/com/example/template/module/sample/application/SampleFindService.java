package com.example.template.module.sample.application;

import com.example.template.module.sample.domain.Sample;
import com.example.template.module.sample.dto.param.SampleFindDto;
import com.example.template.module.sample.repository.SampleQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleFindService {
    private final SampleQueryRepository sampleQueryRepository;

    public SampleFindDto getSample(Long sampleId) {
        Sample sample = sampleQueryRepository.findById(sampleId).orElseThrow(EntityNotFoundException::new);
        return new SampleFindDto(sample);
    }
}
