package com.medicalcenterapi.model.scores;

import com.medicalcenterapi.model.users.Patient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Measures {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private double weight;
    private double height;
    private Integer age;
    private String gender;
    private String BMI;
    private double LBM;
    private double TBF;
    @OneToOne
    private Patient patient;

    public Measures() {
    }
    public Measures(Long id, double weight, double height, Integer age, String gender, String BMI, double LBM, double TBF, Patient patient) {
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.BMI = BMI;
        this.LBM = LBM;
        this.TBF = TBF;
        this.patient = patient;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public double getLBM() {
        return LBM;
    }

    public void setLBM(double LBM) {
        this.LBM = LBM;
    }

    public double getTBF() {
        return TBF;
    }

    public void setTBF(double TBF) {
        this.TBF = TBF;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}