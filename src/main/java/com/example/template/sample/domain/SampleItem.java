package com.example.template.sample.domain;

import com.example.template.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleItem extends BaseEntity {
    @Id
    private Long id;
    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_id")
    private Sample sample;

    public void changeSample(Sample sample) {
        this.sample = sample;
        sample.getSampleItemList().add(this);
    }
}
