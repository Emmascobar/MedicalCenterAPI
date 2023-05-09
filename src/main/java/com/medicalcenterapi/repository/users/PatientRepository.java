package com.medicalcenterapi.repository.users;

import com.medicalcenterapi.model.users.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByNameOrSurname(String name, String surname);
    Optional<Patient> findByNin(String nin);
}