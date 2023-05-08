package com.medicalcenterapi.controller.Impl;

import com.medicalcenterapi.controller.interfaces.AdminController;
import com.medicalcenterapi.model.users.*;
import com.medicalcenterapi.model.utils.Insurance;
import com.medicalcenterapi.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {

    @Autowired
    AdminService adminService;

    /** POST ROUTES **/
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addNewAdmin(Authentication authentication, @RequestBody @Valid Admin admin) {
        return adminService.addNewAdmin(admin);
    }

    @PostMapping("/users/doctors")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor addNewDoctor(Authentication authentication, @RequestBody @Valid Doctor doctor) {
        return adminService.addNewDoctor(doctor);
    }

    @PostMapping("/users/receptionist")
    @ResponseStatus(HttpStatus.CREATED)
    public Receptionist addNewReceptionist(Authentication authentication, @RequestBody @Valid Receptionist receptionist) {
        return adminService.addNewReceptionist(receptionist);
    }


    @PostMapping("/users/pharmacy")
    @ResponseStatus(HttpStatus.CREATED)
    public Pharmacy addNewPharmacy(Authentication authentication, @RequestBody @Valid Pharmacy pharmacy) {
        return adminService.addNewPharmacy(pharmacy);
    }
    @PostMapping("/insurances")
    @ResponseStatus(HttpStatus.CREATED)
    public Insurance addNewInsurance(Authentication authentication, @RequestBody @Valid Insurance insurance) {
        return adminService.addNewInsurance(insurance);
    }

    /** GET ROUTES **/
    @GetMapping("/insurances")
    @ResponseStatus(HttpStatus.OK)
    public List<Insurance> getInsuranceList() {
        return adminService.getInsuranceList();
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> getUserById(@PathVariable Long id) {
        return adminService.getUserById(id);
    }
}
