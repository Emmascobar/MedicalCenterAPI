package com.medicalcenterapi.controller.Impl;

import com.medicalcenterapi.controller.interfaces.PharmacyController;
import com.medicalcenterapi.model.utils.Drug;
import com.medicalcenterapi.services.interfaces.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PharmacyControllerImpl implements PharmacyController {

    @Autowired
    PharmacyService pharmacyService;

    /** PATCH ROUTES **/
    @PatchMapping("/pharmacy/deliver-drugs/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deliverDrugs(@PathVariable(name = "id") Long patientId) {
        pharmacyService.deliverDrugs(patientId);
    }

    @PatchMapping("/pharmacy/deliver-drugs/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void increaseStock(@RequestParam String drugName,@RequestParam Integer quantity) {
        pharmacyService.increaseStock(drugName, quantity);
    }

    /** POST ROUTES **/
    @PostMapping("/pharmacy/add-drugs/")
    @ResponseStatus(HttpStatus.CREATED)
    public Drug addNewDrug(@RequestParam String drugName, @RequestBody Drug drug) {
        return pharmacyService.addNewDrug(drugName, drug);
    }
}
