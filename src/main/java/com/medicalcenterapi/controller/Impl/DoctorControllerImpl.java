package com.medicalcenterapi.controller.Impl;

import com.medicalcenterapi.controller.dto.BloodPressureRegistryDTO;
import com.medicalcenterapi.controller.interfaces.DoctorController;
import com.medicalcenterapi.model.scores.BloodPressureRegistry;
import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.services.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DoctorControllerImpl implements DoctorController {

    @Autowired
    DoctorService doctorService;

    /** GET ROUTES **/
    @GetMapping("/doctors/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients() {
        return doctorService.findAllPatients();
    }

    @GetMapping("/doctors/patients/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return doctorService.findPatientById(id);
    }

    @GetMapping("/doctors/patients/get")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> getPatientByNameOrSurname(@RequestParam(required = false)  String name, @RequestParam(required = false)  String surname) {
        return doctorService.findPatientByNameOrSurname(name, surname);
    }
    @GetMapping("/doctors/patients/nin/{nin}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> getPatientByNin(@PathVariable String nin) {
        return doctorService.findPatientByNin(nin);
    }

    /** POST ROUTES **/
    @PostMapping("/doctors/patients/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient addNewPatient(Authentication authentication, @RequestBody Patient patient) {
        return doctorService.addNewPatient(patient);
    }

    @PostMapping(value = "/doctors/patients/blood-pressure/")
    @ResponseStatus(HttpStatus.CREATED)
    public BloodPressureRegistry BPClassification(@RequestParam Long patientId, @RequestBody BloodPressureRegistryDTO bloodPressureRegistryDTO) {
        return doctorService.BPClassification(patientId, bloodPressureRegistryDTO);
    }
}
