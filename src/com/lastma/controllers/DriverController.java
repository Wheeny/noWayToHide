package com.lastma.controllers;

import com.lastma.data.models.Driver;
import com.lastma.dtos.requests.FindDriverRequest;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.responses.FindDriverResponse;
import com.lastma.dtos.responses.RegisterDriverResponse;
import com.lastma.services.DriverService;
import com.lastma.services.DriverServiceImpl;

import java.util.List;

    public class DriverController {

        private DriverService driverService = new DriverServiceImpl();

        public RegisterDriverResponse registerDriverController(RegisterDriverRequest request) {
            try {
                return driverService.registerDriver(request);
            } catch (IllegalArgumentException error) {
                System.out.println("Driver Registration Error: " + error.getMessage());
                return null;
            }
        }

        public FindDriverResponse findDriverByIdController(FindDriverRequest findDriverRequest) {
            try {
                return driverService.findDriverById(findDriverRequest);
            } catch (IllegalArgumentException error) {
                System.out.println("Driver Lookup Error: " + error.getMessage());
                return null;
            }
        }

        public List<Driver> findAllDriversController() {
            try {
                return driverService.findAllDrivers();
            } catch (Exception error) {
                System.out.println("Error fetching drivers: " + error.getMessage());
                return null;
            }
        }
    }


