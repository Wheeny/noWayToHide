package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.models.Vehicle;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.data.repositories.VehicleRepositoryImpl;
import java.util.List;

    public class VehicleServiceImpl implements VehicleService {
        
        private VehicleRepository vehicleRepo = new VehicleRepositoryImpl();
        private DriverRepository driverRepo = new DriverRepositoryImpl();

        @Override
        public void registerVehicle(String registrationNumber, String make, String model, String colour, int driverId) {
            Driver driver = driverRepo.findById(driverId);
            if (driver == null) {
                throw new IllegalArgumentException("Driver does not exist. Cannot register vehicle.");
            }

            Vehicle existingVehicle = vehicleRepo.findByRegistrationNumber(registrationNumber);
            if (existingVehicle != null) {
                throw new IllegalArgumentException("Vehicle with registration number " + registrationNumber + " already exists");
            }

            Vehicle vehicle = new Vehicle();
            vehicle.setRegistrationNumber(registrationNumber);
            vehicle.setMake(make);
            vehicle.setModel(model);
            vehicle.setColour(colour);
            vehicle.setDriverId(driverId);
            vehicleRepo.save(vehicle);
        }

        @Override
        public Vehicle findVehicleByRegistrationNumber(String regNum) {
            Vehicle found = vehicleRepo.findByRegistrationNumber(regNum);
            if (found == null) {
                throw new IllegalArgumentException("Vehicle not found");
            }
            return found;
        }

        @Override
        public List<Vehicle> findVehiclesByDriverId(int driverId) {
            return vehicleRepo.findByDriverId(driverId);
        }
    }

