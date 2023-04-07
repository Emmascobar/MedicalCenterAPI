package com.medicalcenterapi.repository.utils;

import com.medicalcenterapi.model.utils.ComplementaryTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplementaryTestRepository extends JpaRepository<ComplementaryTest, Long> {
}