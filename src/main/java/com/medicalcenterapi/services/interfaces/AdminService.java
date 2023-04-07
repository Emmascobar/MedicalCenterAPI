package com.medicalcenterapi.services.interfaces;

import com.medicalcenterapi.model.users.*;
import com.medicalcenterapi.model.utils.Insurance;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    // ADMIN Permission:
    //-	Create another Admin Users.
    //-	Create Doctors, Patients, Secretary & Pharmacy Users.
    //-	He can find All Users and find by ID to.
    //- Watch Insurance List and add new ones.
    Admin addNewAdmin(Admin admin);
    Doctor addNewDoctor(Doctor doctor);
    Receptionist addNewReceptionist(Receptionist receptionist);
    Pharmacy addNewPharmacy(Pharmacy pharmacy);
    List<Insurance> getInsuranceList();
    Insurance addNewInsurance(Insurance insurance);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
}
