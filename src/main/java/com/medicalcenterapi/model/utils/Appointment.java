package com.medicalcenterapi.model.utils;

import com.medicalcenterapi.model.users.Doctor;
import com.medicalcenterapi.model.users.Patient;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "appoinments")
public class Appointment {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final LocalDate CREATION_DATE = LocalDate.now();
    @NotEmpty
    @Temporal(TemporalType.DATE)
    private LocalDate appointmentDate;
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    @NotEmpty(message = "Patient can't be empty")
    private Patient patient;
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    @NotEmpty(message = "Doctor can't be empty")
    private Doctor doctor;
    public Appointment() {
    }

    public Appointment(LocalDate appointmentDate, Patient patient, Doctor doctor) {
        this.appointmentDate = appointmentDate;
        this.patient = null;
        this.doctor = doctor;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
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
}
