package com.medicalcenterapi.model.users;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
    }
}
