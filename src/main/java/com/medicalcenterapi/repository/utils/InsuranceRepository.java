package com.medicalcenterapi.repository.utils;

import com.medicalcenterapi.model.utils.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}