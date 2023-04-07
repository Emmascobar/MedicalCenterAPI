package com.medicalcenterapi.repository.scores;

import com.medicalcenterapi.model.scores.BloodPressureRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodPressureRegistryRepository extends JpaRepository<BloodPressureRegistry, Long> {


}