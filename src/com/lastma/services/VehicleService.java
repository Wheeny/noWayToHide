package com.lastma.services;

import com.lastma.data.models.Vehicle;
import java.util.List;

    public interface VehicleService {

        void registerVehicle(String registrationNumber, String make, String model, String colour, int motoristId);

        Vehicle findVehicleByRegistrationNumber(String regNum);

        List<Vehicle> findVehiclesByDriverId(int motoristId);
    }

