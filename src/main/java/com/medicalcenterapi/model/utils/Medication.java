package com.medicalcenterapi.model.utils;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "medications")
public class Medication implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name is necesary")
    private String name;
    @NotEmpty(message = "Presentation is necesary")
    private String presentation;
    private Integer stock;

    private Double price;
    public Medication() {
    }

    public Medication(String name, String presentation, Integer stock, Double price) {
        this.name = name;
        this.presentation = presentation;
        this.stock = stock;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
