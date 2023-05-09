package com.medicalcenterapi.model.utils;

import com.medicalcenterapi.model.others.ItemPharmacyOrder;
import com.medicalcenterapi.model.users.Doctor;
import com.medicalcenterapi.model.users.Patient;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "pharmacy_orders")
public class PharmacyOrder {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final LocalDate DATE = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "patient")
    @NotEmpty(message = "Patient can't be empty")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor")
    @NotEmpty(message = "Doctor can't be empty")
    private Doctor doctor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pharmacy_order_id")
    private List<ItemPharmacyOrder> items;
    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    private Double totalPrice;
    private LocalDate pickUpDate;

    public PharmacyOrder() {
        this.items = new ArrayList<>();
    }

    public PharmacyOrder(Patient patient, Doctor doctor, List<ItemPharmacyOrder> items, Insurance insurance, Double totalPrice, LocalDate pickUpDate) {
        this.patient = patient;
        this.doctor = doctor;
        this.items = items;
        this.insurance = insurance;
        this.totalPrice = totalPrice;
        this.pickUpDate = pickUpDate;
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

    public List<ItemPharmacyOrder> getItems() {
        return items;
    }

    public void setItems(List<ItemPharmacyOrder> items) {
        this.items = items;
    }

    //With the next method we can save each medication drug, one to one.
    public void addItemPharmacyOrder(ItemPharmacyOrder itemPharmacyOrder) {
        items.add(itemPharmacyOrder);
    }

    public Double getTotal() {
        Double total = 0.0;
        int size = items.size();

        for (int i = 0; i < size; i++) {
            total += items.get(i).getCost();
        }
        return total;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        int size = items.size();

        for (int i = 0; i < size; i++) {
            totalPrice += items.get(i).getCost();
        }
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