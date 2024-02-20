package com.example.template.sample.domain;

import com.example.template.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SampleItem extends BaseEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_id")
    private Sample sample;

    public void changeSample(Sample sample) {
        this.sample = sample;
        sample.getSampleItemList().add(this);
    }
}
