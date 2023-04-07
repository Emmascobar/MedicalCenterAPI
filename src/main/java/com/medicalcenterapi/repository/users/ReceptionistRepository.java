package com.medicalcenterapi.repository.users;

import com.medicalcenterapi.model.users.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {
}