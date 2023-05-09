package com.medicalcenterapi.model.others;

import com.medicalcenterapi.model.utils.Medication;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "orders_items")
public class ItemPharmacyOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id")
    private Medication medication;
    private Integer quantity;

    public void setMedication(Medication medication) {
        this.medication = medication;
    }
    public ItemPharmacyOrder() {
    }
    public ItemPharmacyOrder(Medication medication, Integer quantity) {
        this.medication = medication;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }


    public Medication getMedication() {
        return medication;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getCost(){
        return quantity.doubleValue() * medication.getPrice();
    }
}