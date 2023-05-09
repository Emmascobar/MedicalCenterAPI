package com.medicalcenterapi.controller.interfaces;

import com.medicalcenterapi.model.utils.Medication;

public interface PharmacyController {
    // PHARMACY Permission:
    //-	Add Drugs in Store / Manage Stock
    //- Check and Delivery Drugs to patients from Pharmacy Orders create for Doctors.
    void deliverMedication(Long PatientId);
    Medication addNewMedication (String nameOfDrug, Medication medication);
    void increaseStock (String nameOfMedication, Integer quantity);
}
