package com.example.template.sample.domain;

import com.example.template.global.entity.BaseEntity;
import com.example.template.sample.converter.SampleLegacyConverter;
import com.example.template.sample.enums.SampleLegacy;
import com.example.template.sample.enums.SampleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "samples")
public class Sample extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sample_status", nullable = false)
    private SampleStatus sampleStatus;

    @Convert(converter = SampleLegacyConverter.class)
    @Column(name = "sample_legacy", nullable = false)
    private SampleLegacy sampleLegacy;

    @OneToMany(mappedBy = "sample")
    @Builder.Default
    private List<SampleItem> sampleItemList = new ArrayList<>();

}
