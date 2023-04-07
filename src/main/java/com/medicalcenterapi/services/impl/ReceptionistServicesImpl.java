package com.medicalcenterapi.services.impl;

import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.utils.Appointment;
import com.medicalcenterapi.repository.users.PatientRepository;
import com.medicalcenterapi.repository.utils.AppointmentRepository;
import com.medicalcenterapi.services.interfaces.ReceptionistServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class ReceptionistServicesImpl implements ReceptionistServices {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }


    public Optional<Patient> findPatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        return optionalPatient;
    }

    public Optional<Patient> findPatientByNameOrSurname(String name, String surname) {
        Optional<Patient> optionalPatient = patientRepository.findByNameOrSurname(name, surname);
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        return optionalPatient;
    }

    public Optional<Patient> findPatientByNin(String nin) {
        Optional<Patient> optionalPatient = patientRepository.findByNin(nin);
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        return optionalPatient;
    }

    public Appointment addNewAppointment(Long id, Appointment appointment) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't give this appointment because patient not found");
        }
        appointment.setPatient(optionalPatient.get());
        appointment.setDoctor(appointment.getDoctor());
        return appointmentRepository.save(appointment);
    }
}
