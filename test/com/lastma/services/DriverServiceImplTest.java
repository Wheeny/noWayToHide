package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    class DriverServiceImplTest {

        private DriverRepository driverRepository = new DriverRepositoryImpl();
        private DriverService driverService;

        @BeforeEach
        public void setUp() {
            driverService = new DriverServiceImpl();
            driverRepository.deleteAll();
        }

        @Test
        public void testThatDriverCanBeRegistered() {
            assertEquals(0, driverRepository.count());
            driverService.registerDriver("Winifred Nnaji", "08012345678", "winifred@gmail.com");
            assertEquals(1, driverRepository.count());
        }

        @Test
        public void testThatFindingNonExistentDriverThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> {
                driverService.findDriverById(99);
            });
        }
    }
    

