package com.medicalcenterapi.model.users;

import com.medicalcenterapi.model.others.Address;
import com.medicalcenterapi.model.utils.*;
import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends User{

    @NotEmpty(message = "Insert a name")
    private String name;
    @NotEmpty(message = "Insert a surname")
    private String surname;
    @NotEmpty(message = "nin number is empty")
    private String nin;
    @NotEmpty(message = "please, insert a birthday")
    private LocalDate dayOfBirth;
    @NotEmpty(message = "Sex & Genre are mandatory")
    private String sex;
    @NotEmpty(message = "Sex & Genre are mandatory")
    private String genre;
    @Embedded
    private Address address;
    @NotEmpty(message = "insert a mobil phone number")
    private Long telephoneNumber;
    private String email;
    @ManyToMany
    private List<Doctor> doctors;
    @ManyToOne
    private Insurance insurance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Appointment> appointments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<ComplementaryTest> complementaryTests;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<MedicalReport> medicalReports;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "patient")
    private PharmacyOrder pharmacyOrder;

    public Patient() {
    }

    public Patient(String username, String password, String name, String surname, String nin, LocalDate dayOfBirth, String sex, String genre, Long telephoneNumber, Address address, String email, List<Doctor> doctors, Insurance insurance, List<Appointment> appointments, List<ComplementaryTest> complementaryTests, List<MedicalReport> medicalReports, PharmacyOrder pharmacyOrder) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.nin = nin;
        this.dayOfBirth = dayOfBirth;
        this.sex = sex;
        this.genre = genre;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.email = email;
        this.doctors = doctors;
        this.insurance = insurance;
        this.appointments = appointments;
        this.complementaryTests = complementaryTests;
        this.medicalReports = medicalReports;
        this.pharmacyOrder = pharmacyOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<ComplementaryTest> getComplementaryTests() {
        return complementaryTests;
    }

    public void setComplementaryTests(List<ComplementaryTest> complementaryTests) {
        this.complementaryTests = complementaryTests;
    }

    public List<MedicalReport> getMedicalReports() {
        return medicalReports;
    }

    public void setMedicalReports(List<MedicalReport> medicalReports) {
        this.medicalReports = medicalReports;
    }

    public PharmacyOrder getPharmacyOrder() {
        return pharmacyOrder;
    }

    public void setPharmacyOrder(PharmacyOrder pharmacyOrder) {
        this.pharmacyOrder = pharmacyOrder;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}