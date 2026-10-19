package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.data.repositories.VehicleRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleServiceImplTest {

    private VehicleRepository vehicleRepository = new VehicleRepositoryImpl();
    private DriverRepository driverRepository = new DriverRepositoryImpl();
    private VehicleService vehicleService;

    @BeforeEach
    public void setUp() {
        vehicleService = new VehicleServiceImpl();
        vehicleRepository.deleteAll();
        driverRepository.deleteAll();
    }



        @Test
        public void testThatVehicleCanBeRegisteredForExistingDriver() {
            Driver driver1 = new Driver();
            driver1.setFullName("Winifred");
            driver1.setEmail("winiefred@gmail.com");
            driverRepository.save(driver1);

            assertEquals(0, vehicleRepository.count());
            vehicleService.registerVehicle("LSR-123-XY", "Toyota", "Camry", "Black", driver1.getId());
            assertEquals(1, vehicleRepository.count());
        }

        @Test
        public void testThatVehicleRegistrationFailsIfDriverDoesNotExist() {
            assertThrows(IllegalArgumentException.class, () -> {
                vehicleService.registerVehicle("LSR-123-XY", "Toyota", "Camry", "Black", 999);
            });
        }
    }

