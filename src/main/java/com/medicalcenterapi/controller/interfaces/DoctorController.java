package com.medicalcenterapi.controller.interfaces;

import com.medicalcenterapi.controller.dto.BloodPressureRegistryDTO;
import com.medicalcenterapi.model.scores.*;
import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.utils.ComplementaryTest;
import com.medicalcenterapi.model.utils.MedicalReport;
import com.medicalcenterapi.model.utils.PharmacyOrder;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface DoctorController {
    // DOCTOR Permission:
    //-	Create patients.
    //-	Find all and a specific patient by ID, Name or nin.
    //-	Read, write and uses the tables and scores of system.
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    Optional<Patient> getPatientByNameOrSurname(String name, String surname);
    Optional<Patient>  getPatientByNin(String nin);
    Patient addNewPatient(Authentication authentication, Patient patient);
    PharmacyOrder addNewPharmacyOrder(Long patientId, PharmacyOrder pharmacyOrder);
    ComplementaryTest addNewComplementaryTest(Long patientId, ComplementaryTest complementaryTest);
    MedicalReport addNewMedicalReport(Long patientId, MedicalReport medicalReport);
    BloodPressureRegistry BPClassification(Long patientId, BloodPressureRegistryDTO bloodPressureRegistryDTO);
    MedicalReport wellsDVTClassification(Long patientId);
    MedicalReport BMIClassification(Long patientID, Measures measures);
    MedicalReport LBMClassification(Long patientID, Measures measures);

    MedicalReport glasgowComaScale(Long patientID, GlasgowComaScale glasgowComaScale);

    MedicalReport DosageCalculator(Long patientID, String drugName, double weight, double dosageOrdered, double mgOnHand, double mlOnHand);

    MedicalReport DosagePerDoses(Long patientID, String drugName, double weight, double dosageOrdered, Integer timesPerDay, double minRange, double maxRange);

    MedicalReport pregnancyInformation(Long patientID, PregnancyInformation pregnancyInformation);

    MedicalReport CHA2DS2VASScore(Long patientID, CHA2DS2VASScore cha2DS2VASScore);

    MedicalReport RenalFunctionClassification(Long patientID, RenalFunction renalFunction);
}
