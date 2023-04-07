package com.medicalcenterapi.model.utils;

public class ListOfDrugs {

    private Drug drug;
    private Integer quantity;
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