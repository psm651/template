package com.example.template.sample.application;

import com.example.template.sample.domain.Sample;
import com.example.template.sample.dto.param.SampleFindDto;
import com.example.template.sample.repository.SampleQueryRepository;
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
