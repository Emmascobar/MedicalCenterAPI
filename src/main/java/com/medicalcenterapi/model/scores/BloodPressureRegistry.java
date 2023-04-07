package com.medicalcenterapi.model.scores;

import com.medicalcenterapi.model.users.Patient;
import com.medicalcenterapi.model.utils.Comorbidity;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Entity
public class BloodPressureRegistry {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfRegistry;
    private double averagePAS;
    private double averagePAD;
    @Enumerated(EnumType.ORDINAL)
    private Comorbidity comorbidity;
    private String classification;
    private String suggestedTreatment;
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id") // RECORDAR NO ES NECESARIO MAPEAR DE AMBAS PARTES, SUELE DAR ERROR
    private Patient patient;

    public BloodPressureRegistry() {
    }

    public BloodPressureRegistry(LocalDate dateOfRegistry, double averagePAS, double averagePAD, Comorbidity comorbidity, String classification, String suggestedTreatment) {
        this.dateOfRegistry = LocalDate.now();
        this.averagePAS = averagePAS;
        this.averagePAD = averagePAD;
        this.comorbidity = comorbidity;
        this.classification = null;
        this.suggestedTreatment = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAveragePAS() {
        return averagePAS;
    }

    public void setAveragePAS(double averagePAS) {
        this.averagePAS = averagePAS;
    }

    public double getAveragePAD() {
        return averagePAD;
    }

    public void setAveragePAD(double averagePAD) {
        this.averagePAD = averagePAD;
    }

    public Comorbidity getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(Comorbidity comorbidity) {
        this.comorbidity = comorbidity;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSuggestedTreatment() {
        return suggestedTreatment;
    }

    public void setSuggestedTreatment(String suggestedTreatment) {
        this.suggestedTreatment = suggestedTreatment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
