package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.models.Officer;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import com.lastma.data.repositories.OfficerRepository;
import com.lastma.data.repositories.OfficerRepositoryImpl;

public class OfficerServiceImpl implements OfficerService {

   private OfficerRepository officerRepo = new OfficerRepositoryImpl();

    @Override
    public void register(String fullName, String email, String password) {
         Officer officer = new Officer();
         officer.setFullName(fullName);
         officer.setEmail(email);
         officer.setPassword(password);
         officerRepo.save(officer);
    }

    @Override
    public Officer login(String email, String password) {
        Officer foundOfficer = officerRepo.findByEmail(email);
        if (foundOfficer == null) {
            throw new IllegalArgumentException("Officer with email " + email + " does not exist");
        }
        if (foundOfficer.getPassword().equals(password)) {
            foundOfficer.setLoggedIn(true);

            return foundOfficer;
        }
        throw new IllegalArgumentException("Invalid email or password");
    }

    @Override
    public Officer findOfficerById(int id) {
        Officer foundOfficer = officerRepo.findById(id);
        if (foundOfficer == null) {
            throw new IllegalArgumentException("Officer with id " + id + " does not exist");
        }
        return foundOfficer;
    }


}
