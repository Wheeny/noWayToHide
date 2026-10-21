package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import com.lastma.dtos.requests.FindDriverRequest;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.responses.FindDriverResponse;
import com.lastma.dtos.responses.RegisterDriverResponse;
import com.lastma.utils.Mapper;

import java.util.List;

public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepo = new DriverRepositoryImpl();

        @Override
        public RegisterDriverResponse registerDriver(RegisterDriverRequest request) {
            Driver existing = driverRepo.findByEmail(request.getEmail());
            if (existing != null) {
                throw new IllegalArgumentException("Driver with email " + request.getEmail() + " already exists");
            }

            Driver driver = Mapper.mapToRegisterDriver(request);
            driverRepo.save(driver);

            return Mapper.mapToRegisterDriverResponse(driver);
        }

        @Override
        public FindDriverResponse findDriverById(FindDriverRequest request) {
            Driver found = driverRepo.findById(request.getId());
            if (found == null) {
                throw new IllegalArgumentException("Driver with id " + request.getId() + " does not exist");
            }
            return Mapper.mapToFindDriverResponse(found);
        }

        @Override
        public List<Driver> findAllDrivers() {
            return driverRepo.findAll();
        }
}




