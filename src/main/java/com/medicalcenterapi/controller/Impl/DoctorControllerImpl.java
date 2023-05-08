package com.medicalcenterapi.controller.Impl;

import com.medicalcenterapi.controller.dto.BloodPressureRegistryDTO;
import com.medicalcenterapi.controller.interfaces.DoctorController;
import com.medicalcenterapi.model.scores.*;
import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.utils.ComplementaryTest;
import com.medicalcenterapi.model.utils.MedicalReport;
import com.medicalcenterapi.model.utils.PharmacyOrder;
import com.medicalcenterapi.services.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorControllerImpl implements DoctorController {

    @Autowired
    DoctorService doctorService;

    /** GET ROUTES **/
    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients() {
        return doctorService.findAllPatients();
    }

    @GetMapping("/patients/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return doctorService.findPatientById(id);
    }

    @GetMapping("/patients/get")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> getPatientByNameOrSurname(@RequestParam(required = false) String name, @RequestParam(required = false)  String surname) {
        return doctorService.findPatientByNameOrSurname(name, surname);
    }
    @GetMapping("/patients/nin/{nin}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> getPatientByNin(@PathVariable String nin) {
        return doctorService.findPatientByNin(nin);
    }

    /** POST ROUTES **/
    @PostMapping("/patients/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient addNewPatient(Authentication authentication, @RequestBody @Valid Patient patient) {
        return doctorService.addNewPatient(patient);
    }
    @PostMapping("/patients/add-pharmacy-order")
    @ResponseStatus(HttpStatus.CREATED)
    public PharmacyOrder addNewPharmacyOrder(Long patientId, @RequestBody @Valid PharmacyOrder pharmacyOrder) {
        return null;
    }

    @PostMapping("/patients/add-complementary-test")
    @ResponseStatus(HttpStatus.CREATED)
    public ComplementaryTest addNewComplementaryTest(Long patientId, @RequestBody @Valid ComplementaryTest complementaryTest) {
        return null;
    }

    @PostMapping("/patients/add-custom-medical-report")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport addNewMedicalReport(Long patientId, @RequestBody @Valid MedicalReport medicalReport) {
        return null;
    }

    @PostMapping(value = "/patients/blood-pressure/")
    @ResponseStatus(HttpStatus.CREATED)
    public BloodPressureRegistry BPClassification(@RequestParam Long patientId, @RequestBody BloodPressureRegistryDTO bloodPressureRegistryDTO) {
        return doctorService.BPClassification(patientId, bloodPressureRegistryDTO);
    }

    @PostMapping(value = "/patients/dvt-score/")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport wellsDVTClassification(Long patientId) {
        return doctorService.wellsDVTClassification(patientId);
    }

    @PostMapping(value = "/patients/bmi-score/")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport BMIClassification(Long patientID, @RequestBody @Valid Measures measures) {
        return doctorService.BMIClassification(patientID, measures);
    }

    @PostMapping(value = "/patients/lbm-score/")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport LBMClassification(Long patientID, @RequestBody @Valid Measures measures) {
        return null;
    }

    @PostMapping(value = "/patients/glasgow-score/")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport glasgowComaScale(Long patientID, @RequestBody @Valid GlasgowComaScale glasgowComaScale) {
        return null;
    }

    @PostMapping(value = "/patients/drug-dosage/")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport DosageCalculator(Long patientID, String drugName, double weight, double dosageOrdered, double mgOnHand, double mlOnHand) {
        return null;
    }

    @PostMapping(value = "/patients/drug-indications/")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport DosagePerDoses(Long patientID, String drugName, double weight, double dosageOrdered, Integer timesPerDay, double minRange, double maxRange) {
        return null;
    }

    @PostMapping(value = "/patients/pregnancy-info/")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport pregnancyInformation(Long patientID, @RequestBody @Valid  PregnancyInformation pregnancyInformation) {
        return null;
    }

    @PostMapping(value = "/patients/stroke-risk/")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport CHA2DS2VASScore(Long patientID, @RequestBody @Valid CHA2DS2VASScore cha2DS2VASScore) {
        return null;
    }

    @PostMapping(value = "/patients/renal-function")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalReport RenalFunctionClassification(Long patientID, @RequestBody @Valid RenalFunction renalFunction) {
        return null;
    }
}
