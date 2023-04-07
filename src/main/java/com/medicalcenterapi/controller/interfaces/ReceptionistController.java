package com.medicalcenterapi.controller.interfaces;

import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.utils.Appointment;

import java.util.List;
import java.util.Optional;

public interface ReceptionistController {
    // RECEPTIONIST Permission:
    //-	Create and give appointments.
    //-	Find all and a specific patient by ID, Name or nin.
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    Optional<Patient>  getPatientByNameOrSurname(String name, String surname);
    Optional<Patient>  getPatientByNin(String nin);
    Appointment postNewAppointment(Long id, Appointment appointment);
}
