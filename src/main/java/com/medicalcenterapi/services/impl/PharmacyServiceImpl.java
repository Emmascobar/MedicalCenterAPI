package com.medicalcenterapi.services.impl;

import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.utils.Medication;
import com.medicalcenterapi.repository.users.PatientRepository;
import com.medicalcenterapi.repository.utils.MedicationRepository;
import com.medicalcenterapi.repository.utils.PharmacyOrderRepository;
import com.medicalcenterapi.services.interfaces.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
@Service
public class PharmacyServiceImpl implements PharmacyService {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PharmacyOrderRepository pharmacyOrderRepository;
    @Autowired
    MedicationRepository medicationRepository;

    public void deliverDrugs(Long PatientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(PatientId);
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
//        Integer existStock = optionalPatient.get().getPharmacyOrder().getListOfDrugs().stream().mapToInt(x -> x.getDrug().getStock().;

        // Get total of the order. CHECK!!!
        Double result = optionalPatient.get().getPharmacyOrder().getTotal();
//      Double result = optionalPatient.get().getPharmacyOrder().getListOfDrugs().stream().map(d -> d.getDrug().getPrice()).reduce(000.00, Double::sum);
        // Then Reduce stock
        optionalPatient.get().getPharmacyOrder().getItems().forEach(m -> m.getMedication().setStock(m.getMedication().getStock() - m.getQuantity()));
        // Change the total price on Order
        optionalPatient.get().getPharmacyOrder().setTotalPrice(result);
        // If all are ok, registry this pick up date
        optionalPatient.get().getPharmacyOrder().setPickUpDate(LocalDate.now());

        // Save Changes
        pharmacyOrderRepository.save(optionalPatient.get().getPharmacyOrder());
        patientRepository.save(optionalPatient.get());
    }

    //CHECK HERE! IF THE DRUG SEND BY BODY UPDATE ALL THE PARAMETERS OR ONLY THE EXTRACTS.
    public Medication addNewDrug(String drugName, Medication medication) {
        Optional<Medication> optionalDrug = medicationRepository.findByName(drugName);
        //ONE option here is answer to pharmacy if want update the stock of that drug, and then continue.
        if (optionalDrug.isPresent()) {
            System.out.println("Drug already exist in storage, ¿do you want update the stock? - Y/N ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            // If the drug is already exist in store just update their stock.
            if (command == "Y") {
                System.out.println("OK, now enter the quantity to add in the store");
                String quantity = scanner.nextLine();
                System.out.println("Ok, you add " + quantity + " products in the exist stock. It's OK? - Y/N ");
                String command02 = scanner.nextLine();
                if (command02 == "Y") {
                    optionalDrug.get().setStock(optionalDrug.get().getStock() + Integer.parseInt(quantity));
                    return medicationRepository.save(optionalDrug.get());
                } else {
                    System.out.println("Ok. Thanks! Review drug and stock");
                }
            } else {
                System.out.println("Ok. Thanks! Review drug and stock");
            }
        } else {
            // if not exist, create a new drug in storage.
            medicationRepository.save(medication);
        }
        return medication;
    }

        public void increaseStock (String drugName, Integer quantity){
            Optional<Medication> optionalMedication = medicationRepository.findByName(drugName);
            // Update the Stock.
            optionalMedication.ifPresent(value -> value.setStock(value.getStock() + quantity));

        }
    }
