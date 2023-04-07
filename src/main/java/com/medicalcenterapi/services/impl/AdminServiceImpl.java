package com.medicalcenterapi.services.impl;

import com.medicalcenterapi.model.users.*;
import com.medicalcenterapi.model.utils.Insurance;
import com.medicalcenterapi.repository.scores.BloodPressureRegistryRepository;
import com.medicalcenterapi.repository.users.*;
import com.medicalcenterapi.repository.utils.InsuranceRepository;
import com.medicalcenterapi.services.interfaces.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private BloodPressureRegistryRepository bloodPressureRegistryRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    String encodedPassword;
    Role role;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Autowired
    private PharmacyRepository pharmacyRepository;

    /** METHODS **/
    public Admin addNewAdmin(Admin admin) {
        encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        admin.setRoles(List.of(new Role("ADMIN"), new Role("USER")));
        adminRepository.save(admin);
        return admin;
    }

    public Doctor addNewDoctor(Doctor doctor) {
        encodedPassword = passwordEncoder.encode(doctor.getPassword());
        doctor.setPassword(encodedPassword);
        doctor.setRoles(List.of(new Role("DOCTOR")));
        doctorRepository.save(doctor);
        return doctor;
    }

    public Receptionist addNewReceptionist(Receptionist receptionist) {
        encodedPassword = passwordEncoder.encode(receptionist.getPassword());
        receptionist.setPassword(encodedPassword);
        receptionist.setRoles(List.of(new Role("RECEPTIONIST")));
        receptionistRepository.save(receptionist);
        return receptionist;
    }

    public Pharmacy addNewPharmacy(Pharmacy pharmacy) {
        encodedPassword = passwordEncoder.encode(pharmacy.getPassword());
        pharmacy.setPassword(encodedPassword);
        pharmacy.setRoles(List.of(new Role("PHARMACY")));
        pharmacyRepository.save(pharmacy);
        return pharmacy;
    }

    public List<Insurance> getInsuranceList() {
        return insuranceRepository.findAll();
    }

    public Insurance addNewInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
        return insurance ;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        if (getUserById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return userRepository.findById(id);
    }
}
