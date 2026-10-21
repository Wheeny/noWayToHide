package com.lastma.controllers;

import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.responses.RegisterVehicleResponse;
import com.lastma.dtos.responses.VehicleResponse;
import com.lastma.services.VehicleService;
import com.lastma.services.VehicleServiceImpl;

import java.util.List;

public class VehicleController {

    private VehicleService vehicleService = new VehicleServiceImpl();

    public RegisterVehicleResponse registerVehicleController(RegisterVehicleRequest request) {
        try {
            return vehicleService.registerVehicle(request);
        } catch (IllegalArgumentException error) {
            System.out.println("Vehicle Registration Error: " + error.getMessage());
            return null;
        }
    }

    public VehicleResponse findVehicleByRegistrationNumberController(String regNum) {
        try {
            return vehicleService.findVehicleByRegistrationNumber(regNum);
        } catch (IllegalArgumentException error) {
            System.out.println("Vehicle Lookup Error: " + error.getMessage());
            return null;
        }
    }

    public List<VehicleResponse> findVehiclesByDriverIdController(int driverId) {
        try {
            return vehicleService.findVehiclesByDriverId(driverId);
        } catch (Exception error) {
            System.out.println("Error fetching vehicles for driver: " + error.getMessage());
            return null;
        }
    }
}