package com.medicalcenterapi.repository.utils;

import com.medicalcenterapi.model.utils.PharmacyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyOrderRepository extends JpaRepository<PharmacyOrder, Long> {
}