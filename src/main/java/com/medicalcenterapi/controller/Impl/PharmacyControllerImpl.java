package com.medicalcenterapi.controller.Impl;

import com.medicalcenterapi.controller.interfaces.PharmacyController;
import com.medicalcenterapi.model.utils.Medication;
import com.medicalcenterapi.services.interfaces.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyControllerImpl implements PharmacyController {

    @Autowired
    PharmacyService pharmacyService;

    /** PATCH ROUTES **/
    @PatchMapping("/deliver-medication/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deliverMedication(@PathVariable(name = "id") Long patientId) {
        pharmacyService.deliverDrugs(patientId);
    }

    @PatchMapping("/update-stock/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void increaseStock(@RequestParam String drugName,@RequestParam Integer quantity) {
        pharmacyService.increaseStock(drugName, quantity);
    }

    /** POST ROUTES **/
    @PostMapping("/add-medications/")
    @ResponseStatus(HttpStatus.CREATED)
    public Medication addNewMedication(@RequestParam String drugName, @RequestBody Medication medication) {
        return pharmacyService.addNewDrug(drugName, medication);
    }
}
