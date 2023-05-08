package com.medicalcenterapi.controller.Impl;

import com.medicalcenterapi.controller.interfaces.PatientController;
import com.medicalcenterapi.model.utils.Appointment;
import com.medicalcenterapi.services.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientControllerImpl implements PatientController {
    @Autowired
    PatientService patientService;

    /** GET ROUTES **/
    @GetMapping("/appointments")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findAppointments(Long patientId) {
        return patientService.findAppointments(patientId);
    }
}
