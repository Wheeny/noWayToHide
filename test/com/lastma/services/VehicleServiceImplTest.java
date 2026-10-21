package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.data.repositories.VehicleRepositoryImpl;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.responses.RegisterVehicleResponse;
import com.lastma.dtos.responses.VehicleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleServiceImplTest {

    private VehicleRepository vehicleRepository = new VehicleRepositoryImpl();
    private DriverRepository driverRepository = new DriverRepositoryImpl();
    private VehicleService vehicleService;
    private RegisterVehicleRequest registerVehicleRequest;

    @BeforeEach
    public void setUp() {
        vehicleService = new VehicleServiceImpl();
        registerVehicleRequest = new RegisterVehicleRequest();
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
        registerVehicleRequest.setRegistrationNumber("LSR-123-XY");
        registerVehicleRequest.setMake("Toyota");
        registerVehicleRequest.setModel("Camry");
        registerVehicleRequest.setColour("Black");
        registerVehicleRequest.setDriverId(driver1.getId());

        RegisterVehicleResponse response = vehicleService.registerVehicle(registerVehicleRequest);

        assertEquals(1, vehicleRepository.count());
        assertNotNull(response);
        assertEquals("LSR-123-XY", response.getRegistrationNumber());
    }

    @Test
    public void testThatVehicleRegistrationFailsIfDriverDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            registerVehicleRequest.setRegistrationNumber("LSR-123-XY");
            registerVehicleRequest.setMake("Toyota");
            registerVehicleRequest.setModel("Camry");
            registerVehicleRequest.setColour("Black");
            registerVehicleRequest.setDriverId(100); // Non-existent driver ID

            vehicleService.registerVehicle(registerVehicleRequest);
        });
    }

    @Test
    public void testThatDuplicateRegistrationNumberThrowsException() {
        Driver driver1 = new Driver();
        driver1.setFullName("Winifred");
        driver1.setEmail("winiefred@gmail.com");
        driverRepository.save(driver1);

        registerVehicleRequest.setRegistrationNumber("LSR-123-XY");
        registerVehicleRequest.setMake("Toyota");
        registerVehicleRequest.setModel("Camry");
        registerVehicleRequest.setColour("Black");
        registerVehicleRequest.setDriverId(driver1.getId());

        vehicleService.registerVehicle(registerVehicleRequest);

        RegisterVehicleRequest duplicateRequest = new RegisterVehicleRequest();
        duplicateRequest.setRegistrationNumber("LSR-123-XY");
        duplicateRequest.setMake("Honda");
        duplicateRequest.setModel("Accord");
        duplicateRequest.setColour("Silver");
        duplicateRequest.setDriverId(driver1.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            vehicleService.registerVehicle(duplicateRequest);
        });
    }

    @Test
    public void testThatVehicleCanBeFoundByRegistrationNumber() {
        Driver driver1 = new Driver();
        driver1.setFullName("Winifred");
        driver1.setEmail("winiefred@gmail.com");
        driverRepository.save(driver1);

        registerVehicleRequest.setRegistrationNumber("ABC-999-ZZ");
        registerVehicleRequest.setMake("Lexus");
        registerVehicleRequest.setModel("RX350");
        registerVehicleRequest.setColour("White");
        registerVehicleRequest.setDriverId(driver1.getId());
        vehicleService.registerVehicle(registerVehicleRequest);

        VehicleResponse found = vehicleService.findVehicleByRegistrationNumber("ABC-999-ZZ");

        assertNotNull(found);
        assertEquals("Lexus", found.getMake());
        assertEquals("White", found.getColour());
    }

    @Test
    public void testThatFindingNonExistentVehicleByRegistrationNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            vehicleService.findVehicleByRegistrationNumber("UNKNOWN-PLATE");
        });
    }

    @Test
    public void testThatVehiclesCanBeFoundByDriverId() {
        Driver driver1 = new Driver();
        driver1.setFullName("Winifred");
        driver1.setEmail("winiefred@gmail.com");
        driverRepository.save(driver1);

        registerVehicleRequest.setRegistrationNumber("LAG-001-AA");
        registerVehicleRequest.setMake("Toyota");
        registerVehicleRequest.setModel("Corolla");
        registerVehicleRequest.setColour("Blue");
        registerVehicleRequest.setDriverId(driver1.getId());
        vehicleService.registerVehicle(registerVehicleRequest);

        RegisterVehicleRequest request2 = new RegisterVehicleRequest();
        request2.setRegistrationNumber("LAG-002-BB");
        request2.setMake("Kia");
        request2.setModel("Rio");
        request2.setColour("Red");
        request2.setDriverId(driver1.getId());
        vehicleService.registerVehicle(request2);

        List<VehicleResponse> driverVehicles = vehicleService.findVehiclesByDriverId(driver1.getId());

        assertEquals(2, driverVehicles.size());
    }
}

