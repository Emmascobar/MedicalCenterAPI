package com.medicalcenterapi.model.utils;

import com.medicalcenterapi.model.users.Doctor;
import com.medicalcenterapi.model.users.Patient;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PharmacyOrder {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final LocalDate DATE = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "pacient")
    @NotEmpty(message = "Pacient can't be empty")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor")
    @NotEmpty(message = "Doctor can't be empty")
    private Doctor doctor;
    private List<ListOfDrugs> listOfDrugs;
    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    private Double totalPrice;
    private LocalDate pickUpDate;

    public PharmacyOrder() {
    }

    public PharmacyOrder(Patient patient, Doctor doctor, List<ListOfDrugs> listOfDrugs, Integer quantity, Insurance insurance, Double totalPrice, LocalDate pickUpDate) {
        this.patient = patient;
        this.doctor = doctor;
        this.listOfDrugs = listOfDrugs;
        this.insurance = insurance;
        this.totalPrice = null;
        this.pickUpDate = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDATE() {
        return DATE;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<ListOfDrugs> getListOfDrugs() {
        return listOfDrugs;
    }

    public void setListOfDrugs(List<ListOfDrugs> listOfDrugs) {
        this.listOfDrugs = listOfDrugs;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }
}