package com.medicalcenterapi.controller.interfaces;

import com.medicalcenterapi.model.utils.Appointment;

import java.util.List;

public interface PatientController {
    // PATIENTS Permission:
    //-	Find their appointments.
    List<Appointment> findAppointments(Long patientId);
}
