package com.medicalcenterapi.controller.interfaces;

import com.medicalcenterapi.controller.dto.BloodPressureRegistryDTO;
import com.medicalcenterapi.model.scores.BloodPressureRegistry;
import com.medicalcenterapi.model.users.Patient;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface DoctorController {
    // DOCTOR Permission:
    //-	Create patients.
    //-	Find all and a specific patient by ID, Name or nin.
    //-	Read, write and uses the tables and scores of system.
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    Optional<Patient> getPatientByNameOrSurname(String name, String surname);
    Optional<Patient>  getPatientByNin(String nin);
    Patient addNewPatient(Authentication authentication, Patient patient);
    BloodPressureRegistry BPClassification(Long patientId, BloodPressureRegistryDTO bloodPressureRegistryDTO);
}
