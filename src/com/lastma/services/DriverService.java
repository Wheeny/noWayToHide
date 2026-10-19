package com.lastma.services;

import com.lastma.data.models.Driver;
import java.util.List;

public interface DriverService {

        void registerDriver(String fullName, String phoneNumber, String email);

        Driver findDriverById(int id);

        List<Driver> findAllDrivers();
    }


