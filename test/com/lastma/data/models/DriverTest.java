package com.lastma.data.models;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    private Driver driver;
    @BeforeEach
    public void setUp() {
        driver = new Driver();
    }

    @Test
    public void testToGetFullName() {
        driver.setFullName("John Doe");
        assertEquals("John Doe", driver.getFullName());
    }

    @Test
    void setFullName() {
        driver.setFullName("John Doe");
        assertEquals("John Doe", driver.getFullName());
    }

    @Test
    void getEmail() {
        driver.setEmail("John Doe");
        assertEquals("John Doe", driver.getEmail());
    }

    @Test
    void setEmail() {
        driver.setEmail("John Doe");
        assertEquals("John Doe", driver.getEmail());
    }

    @Test
    void getId() {
        driver.setId(1);
        assertEquals(1, driver.getId());
    }

    @Test
    void setId() {
        driver.setId(1);
        assertEquals(1, driver.getId());
    }

    @Test
    void getCarPlateNumber() {
        driver.setCarPlateNumber("123");
        assertEquals("123", driver.getCarPlateNumber());
    }

    @Test
    void setCarPlateNumber() {
        driver.setCarPlateNumber("123");
        assertEquals("123", driver.getCarPlateNumber());
    }
}