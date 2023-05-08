package com.medicalcenterapi.controller.Impl;

import com.medicalcenterapi.controller.interfaces.ReceptionistController;
import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.utils.Appointment;
import com.medicalcenterapi.services.interfaces.ReceptionistServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistControllerImpl implements ReceptionistController {

    @Autowired
    ReceptionistServices receptionistServices;

    /** GET ROUTES **/
    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients() {
        return receptionistServices.findAllPatients();
    }
    @GetMapping("/patients/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return receptionistServices.findPatientById(id);
    }
    @GetMapping("/patients/get/")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> getPatientByNameOrSurname(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        return receptionistServices.findPatientByNameOrSurname(name, surname);
    }
    @GetMapping("/patients/nin/{nin}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> getPatientByNin(@PathVariable String nin) {
        return receptionistServices.findPatientByNin(nin);
    }

    /** POST ROUTES **/
    @PostMapping("/patients/{id}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment postNewAppointment(@PathVariable Long id, @RequestBody @Valid Appointment appointment) {
        return receptionistServices.addNewAppointment(id, appointment);
    }
}
