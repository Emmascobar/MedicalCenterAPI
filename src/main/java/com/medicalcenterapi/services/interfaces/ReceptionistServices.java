package com.medicalcenterapi.services.interfaces;

import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.utils.Appointment;

import java.util.List;
import java.util.Optional;

public interface ReceptionistServices {
    // RECEPTIONIST Permission:
    //-	Create and give appointments.
    //-	Find all and a specific patient by ID, Name or nin.
    List<Patient> findAllPatients();
    Optional<Patient> findPatientById(Long id);
    Optional<Patient>  findPatientByNameOrSurname(String name, String surname);
    Optional<Patient>  findPatientByNin(String nin);
    Appointment addNewAppointment(Long id, Appointment appointment);
}
