package com.medicalcenterapi.repository.users;

import com.medicalcenterapi.model.utils.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {
}