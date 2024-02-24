package com.example.template.sample.repository;

import com.example.template.sample.domain.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleCommandRepository extends JpaRepository<Sample, Long> {
}
