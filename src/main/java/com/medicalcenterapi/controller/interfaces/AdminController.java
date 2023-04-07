package com.medicalcenterapi.controller.interfaces;

import com.medicalcenterapi.model.users.*;
import com.medicalcenterapi.model.utils.Insurance;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface AdminController {
    // ADMIN Permission:
    //-	Create another Admin Users.
    //-	Create Doctors, Secretary & Pharmacy Users.
    //-	They can find All Users, Insurances and find by ID too.
    Admin addNewAdmin(Authentication authentication, Admin admin);
    Doctor addNewDoctor(Authentication authentication, Doctor doctor);
    Receptionist addNewReceptionist(Authentication authentication, Receptionist receptionist);
    Pharmacy addNewPharmacy(Authentication authentication, Pharmacy pharmacy);
    Insurance addNewInsurance(Authentication authentication, Insurance insurance);
    List<Insurance> getInsuranceList();
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
}
