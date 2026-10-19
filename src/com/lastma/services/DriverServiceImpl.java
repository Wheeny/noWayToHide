package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import java.util.List;

    public class DriverServiceImpl implements DriverService {
        private DriverRepository driverRepo = new DriverRepositoryImpl();

        @Override
        public void registerDriver(String fullName, String phoneNumber, String email) {
            Driver existing = driverRepo.findByEmail(email);
            if (existing != null) {
                throw new IllegalArgumentException("Driver with email " + email + " already exists");
            }

            Driver driver = new Driver();
            driver.setFullName(fullName);
            driver.setPhoneNumber(phoneNumber);
            driver.setEmail(email);
            driverRepo.save(driver);
        }

        @Override
        public Driver findDriverById(int id) {
            Driver found = driverRepo.findById(id);
            if (found == null) {
                throw new IllegalArgumentException("Driver with id " + id + " does not exist");
            }
            return found;
        }

        @Override
        public List<Driver> findAllDrivers() {
            return driverRepo.findAll();
        }
    }

