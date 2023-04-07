package com.medicalcenterapi.model.utils;

import com.medicalcenterapi.model.users.Patient;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
public class Insurance {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long pharmacyCoverage;
    private Long attentionCoverage;
    private Long complementaryCoverage;
    @OneToMany(mappedBy = "insurance")
    private List<Patient> patients;

    public Insurance() {
    }

    public Insurance(String name, Long pharmacyCoverage, Long attentionCoverage, Long complementaryCoverage, List<Patient> patients) {
        this.name = name;
        this.pharmacyCoverage = pharmacyCoverage;
        this.attentionCoverage = attentionCoverage;
        this.complementaryCoverage = complementaryCoverage;
        this.patients = patients;
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

    public Long getPharmacyCoverage() {
        return pharmacyCoverage;
    }

    public void setPharmacyCoverage(Long pharmacyCoverage) {
        this.pharmacyCoverage = pharmacyCoverage;
    }

    public Long getAttentionCoverage() {
        return attentionCoverage;
    }

    public void setAttentionCoverage(Long attentionCoverage) {
        this.attentionCoverage = attentionCoverage;
    }

    public Long getComplementaryCoverage() {
        return complementaryCoverage;
    }

    public void setComplementaryCoverage(Long complementaryCoverage) {
        this.complementaryCoverage = complementaryCoverage;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
