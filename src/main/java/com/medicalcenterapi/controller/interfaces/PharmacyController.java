package com.medicalcenterapi.controller.interfaces;

import com.medicalcenterapi.model.utils.Drug;

public interface PharmacyController {
    // PHARMACY Permission:
    //-	Add Drugs in Store / Manage Stock
    //- Check and Delivery Drugs to patients from Pharmacy Orders create for Doctors.
    void deliverDrugs (Long PatientId);
    Drug addNewDrug (String nameOfDrug, Drug drug);
    void increaseStock (String nameOfDrug, Integer quantity);
}
