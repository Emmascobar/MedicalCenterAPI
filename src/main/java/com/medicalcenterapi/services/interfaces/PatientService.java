package com.medicalcenterapi.services.interfaces;

import com.medicalcenterapi.model.utils.Appointment;

import java.util.List;

public interface PatientService {
    // PATIENTS Permission:
    //-	Find their appointments.
    List<Appointment> findAppointments(Long patientId);
}
