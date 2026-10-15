package com.lastma.data.repositories;

import com.lastma.data.models.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverRepositoryImplTest {

    private DriverRepositoryImpl driverRepository;
    private Driver driver;

    @BeforeEach
    public void setUp() {
        driverRepository = new DriverRepositoryImpl();
        driver = new Driver();
    }


    @Test
    public void testThatICreateAndSaveADriver_CountIncreases() {
        driverRepository.save(driver);
        assertEquals(1, driverRepository.count());

    }

    @Test
    public void testThatICreateAndSave2Drivers_CountIncreases() {
        driverRepository.save(driver);
        Driver driver2 = new Driver();
        driverRepository.save(driver2);
        assertEquals(2, driverRepository.count());

    }

    @Test
    public void testToFindADriverById() {
        driverRepository.save(driver);
        Driver driver2 = new Driver();
        driverRepository.save(driver2);
        Driver driver3 = new Driver();
        driverRepository.save(driver3);

        Driver foundDriver = driverRepository.findById(2);
        assertEquals(3, driverRepository.count());
        assertEquals(2, foundDriver.getId());
    }

    @Test
    public void testToFindNonExistentDrivers() {
        driverRepository.save(driver);
        Driver driver2 = new Driver();
        driverRepository.save(driver2);
        Driver foundDriver = driverRepository.findById(3);
        assertEquals(2, driverRepository.count());
        assertNull(foundDriver);
    }

    @Test
    public void testToFindAllDrivers() {
        driverRepository.save(driver);
        Driver driver2 = new Driver();
        driverRepository.save(driver2);
        Driver driver3 = new Driver();
        driverRepository.save(driver3);

        List<Driver> drivers = driverRepository.findAll();
        assertEquals(3, drivers.size());

    }

    @Test
    public void testToDeleteDriversById() {
        driverRepository.save(driver);
        Driver driver2 = new Driver();
        driverRepository.save(driver2);
        Driver driver3 = new Driver();
        driverRepository.save(driver3);

        driverRepository.deleteById(2);
        assertEquals(2, driverRepository.count());
    }

    @Test
    public void testToDeleteAllDrivers() {
        driverRepository.save(driver);
        Driver driver2 = new Driver();
        driverRepository.save(driver2);
        Driver driver3 = new Driver();
        driverRepository.save(driver3);

        driverRepository.deleteAll();
        assertEquals(0, driverRepository.count());
    }

    @Test
    public void testThatADriverExistsById() {
        driverRepository.save(driver);
        Driver driver2 = new Driver();
        driverRepository.save(driver2);
        Driver driver3 = new Driver();
        driverRepository.save(driver3);

        boolean foundDriver = driverRepository.existsById(2);
        assertTrue(foundDriver);

    }


}