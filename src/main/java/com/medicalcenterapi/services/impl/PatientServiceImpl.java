package com.medicalcenterapi.services.impl;

import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.utils.Appointment;
import com.medicalcenterapi.repository.users.PatientRepository;
import com.medicalcenterapi.repository.utils.AppointmentRepository;
import com.medicalcenterapi.services.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
//    public List<Appointment> findAppointments(Long patientId) {
//        return appointmentRepository.findByPatientId(patientId);
//    }
    public List<Appointment> findAppointments(Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        List<Appointment> listOfAppointments = optionalPatient.get().getAppointments();
        return listOfAppointments;
    }
}
