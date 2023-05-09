package com.medicalcenterapi.model.users;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pharmacists")
public class Pharmacy extends User {
    @NotEmpty(message = "Insert a name")
    private String name;
    @NotEmpty(message = "Insert a surname")
    private  String surname;
    @NotEmpty(message = "NI number is empty")
    private String nin;
    public Pharmacy() {
    }
    public Pharmacy(String username, String password, String name, String surname, String nin) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.nin = nin;
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

    public String getnin() {
        return nin;
    }

    public void setnin(String nin) {
        this.nin = nin;
    }
}

