package com.example.template.sample.domain;

import com.example.template.sample.converter.SampleLegacyConverter;
import com.example.template.sample.enums.SampleLegacy;
import com.example.template.sample.enums.SampleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "sample")
    private List<SampleItem> sampleItemList = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    private SampleStatus sampleStatus;
    @Convert(converter = SampleLegacyConverter.class)
    private SampleLegacy sampleLegacy;

}
