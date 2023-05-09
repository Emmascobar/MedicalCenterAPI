package com.medicalcenterapi.services.interfaces;

import com.medicalcenterapi.model.utils.Medication;

public interface PharmacyService {
    // PHARMACY Permission:
    //-	Add Drugs in Store / Manage Stock
    //- Check and Delivery Drugs to patients from Pharmacy Orders create for Doctors.
    void deliverDrugs (Long PatientId);
    Medication addNewDrug (String drugName, Medication medication);
    void increaseStock (String drugName, Integer quantity);
}
