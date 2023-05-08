package com.medicalcenterapi.model.others;

import com.medicalcenterapi.model.utils.Drug;
import jakarta.persistence.*;

public class ListOfDrugs {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ListOfDrugs() {
    }

    public ListOfDrugs(Drug drug, Integer quantity) {
        this.drug = drug;
        this.quantity = quantity;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}