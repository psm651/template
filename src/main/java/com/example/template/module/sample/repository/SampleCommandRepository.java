package com.example.template.module.sample.repository;

import com.example.template.module.sample.domain.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleCommandRepository extends JpaRepository<Sample, Long> {
}
