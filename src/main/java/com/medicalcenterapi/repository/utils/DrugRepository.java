package com.medicalcenterapi.repository.utils;

import com.medicalcenterapi.model.utils.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    Optional<Drug> findByName(String name);

}