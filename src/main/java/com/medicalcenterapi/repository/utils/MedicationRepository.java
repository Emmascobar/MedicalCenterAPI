package com.medicalcenterapi.repository.utils;

import com.medicalcenterapi.model.utils.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findByName(String name);

}