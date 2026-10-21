package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.models.Vehicle;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.data.repositories.VehicleRepositoryImpl;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.responses.RegisterVehicleResponse;
import com.lastma.dtos.responses.VehicleResponse;
import com.lastma.utils.Mapper;

import java.util.ArrayList;
import java.util.List;

        public class VehicleServiceImpl implements VehicleService {

            private VehicleRepository vehicleRepo = new VehicleRepositoryImpl();
            private DriverRepository driverRepo = new DriverRepositoryImpl();

            @Override
            public RegisterVehicleResponse registerVehicle(RegisterVehicleRequest registerVehicleRequest) {
                Driver driver = driverRepo.findById(registerVehicleRequest.getDriverId());
                if (driver == null) {
                    throw new IllegalArgumentException("Driver does not exist. Cannot register vehicle.");
                }

                Vehicle existingVehicle = vehicleRepo.findByRegistrationNumber(registerVehicleRequest.getRegistrationNumber());
                if (existingVehicle != null) {
                    throw new IllegalArgumentException("Vehicle with registration number " + registerVehicleRequest.getRegistrationNumber() + " already exists");
                }

                Vehicle mappedVehicle = Mapper.mapToRegisterVehicle(registerVehicleRequest);
                vehicleRepo.save(mappedVehicle);

                return Mapper.mapToRegisterVehicleResponse(mappedVehicle);
            }

            @Override
            public VehicleResponse findVehicleByRegistrationNumber(String regNum) {
                Vehicle found = vehicleRepo.findByRegistrationNumber(regNum);
                if (found == null) {
                    throw new IllegalArgumentException("Vehicle with registration number " + regNum + " not found");
                }
                return Mapper.mapToVehicleResponse(found);
            }

            @Override
            public List<VehicleResponse> findVehiclesByDriverId(int driverId) {
                List<Vehicle> vehicles = vehicleRepo.findByDriverId(driverId);
                List<VehicleResponse> responses = new ArrayList<>();

                for (int count = 0; count < vehicles.size(); count++) {
                    responses.add(Mapper.mapToVehicleResponse(vehicles.get(count)));
                }

                return responses;
            }

        }

