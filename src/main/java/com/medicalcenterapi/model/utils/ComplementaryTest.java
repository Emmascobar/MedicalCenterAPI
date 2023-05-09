package com.medicalcenterapi.model.utils;

import com.medicalcenterapi.model.others.ServiceArea;
import com.medicalcenterapi.model.users.Doctor;
import com.medicalcenterapi.model.users.Patient;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "complementary_tests")
public class ComplementaryTest {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final LocalDate CREATION_DATE = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "patient")
    @NotEmpty(message = "Patient can't be empty")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor")
    @NotEmpty(message = "Doctor can't be empty")
    private Doctor doctor;
    @Enumerated(EnumType.STRING)
    private ServiceArea serviceArea;
    @NotEmpty
    private LocalDate appointmentDate;
    private BigDecimal totalPrice;
    public ComplementaryTest() {
    }

    public ComplementaryTest(Patient patient, Doctor doctor, ServiceArea serviceArea, LocalDate appointmentDate, BigDecimal totalPrice) {
        this.patient = patient;
        this.doctor = doctor;
        this.serviceArea = serviceArea;
        this.appointmentDate = appointmentDate;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ServiceArea getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(ServiceArea serviceArea) {
        this.serviceArea = serviceArea;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
