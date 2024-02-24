package com.example.template.sample.repository;

import com.example.template.global.config.QueryDSLConfig;
import com.example.template.sample.domain.Sample;
import com.example.template.sample.domain.SampleItem;
import com.example.template.sample.enums.SampleLegacy;
import com.example.template.sample.enums.SampleStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({QueryDSLConfig.class})
class SampleQueryRepositoryTest {
    @Autowired
    private SampleQueryRepository sampleQueryRepository;
    @Autowired
    private SampleCommandRepository sampleCommandRepository;
    private Sample savedSample;
    private SampleItem sampleItem;

    @BeforeEach
    public void setUp() {
        sampleItem = SampleItem.builder()
                .name("sampleItemName")
                .build();
        Sample newSample = Sample.builder()
                .name("testName")
                .sampleItemList(List.of(sampleItem))
                .sampleStatus(SampleStatus.GOOD)
                .sampleLegacy(SampleLegacy.MEANINGFUL_WORD)
                .build();
        savedSample = sampleCommandRepository.save(newSample);
    }

    @Test
    @DisplayName("sample 조회")
    void saveOne() {

        Optional<Sample> optionalSample = sampleQueryRepository.findById(savedSample.getId());

        assertThat(optionalSample.isPresent()).isTrue();

        Sample findedSample = optionalSample.get();
        assertThat(findedSample.getSampleLegacy()).isEqualTo(savedSample.getSampleLegacy());
        assertThat(findedSample.getName()).isEqualTo(savedSample.getName());
        assertThat(findedSample.getSampleStatus()).isEqualTo(savedSample.getSampleStatus());
        assertThat(findedSample.getSampleItemList()).isEqualTo(savedSample.getSampleItemList());
    }

}