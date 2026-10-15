package com.lastma.data.repositories;

import com.lastma.data.models.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryImplTest {

        private VehicleRepositoryImpl vehicleRepository;
        private Vehicle vehicle;

        @BeforeEach
        public void setUp() {
            vehicleRepository = new VehicleRepositoryImpl();
            vehicle = new Vehicle();
        }


        @Test
        public void testThatICreateAndSaveAVehicle_CountIncreases() {
            vehicleRepository.save(vehicle);
            assertEquals(1, vehicleRepository.count());

        }

        @Test
        public void testThatICreateAndSave2Vehicles_CountIncreases() {
            vehicleRepository.save(vehicle);
            Vehicle vehicle2 = new Vehicle();
            vehicleRepository.save(vehicle2);
            assertEquals(2, vehicleRepository.count());

        }

        @Test
        public void testToFindAVehicleById() {
            vehicleRepository.save(vehicle);
            Vehicle vehicle2 = new Vehicle();
            vehicleRepository.save(vehicle2);
            Vehicle vehicle3 = new Vehicle();
            vehicleRepository.save(vehicle3);

            Vehicle foundVehicle = vehicleRepository.findById(2);
            assertEquals(3, vehicleRepository.count());
            assertEquals(2, foundVehicle.getId());
        }

        @Test
        public void testToFindNonExistentVehicles() {
            vehicleRepository.save(vehicle);
            Vehicle vehicle2 = new Vehicle();
            vehicleRepository.save(vehicle2);
            Vehicle foundVehicle = vehicleRepository.findById(3);
            assertEquals(2, vehicleRepository.count());
            assertNull(foundVehicle);
        }

        @Test
        public void testToFindAllVehicles() {
            vehicleRepository.save(vehicle);
            Vehicle vehicle2 = new Vehicle();
            vehicleRepository.save(vehicle2);
            Vehicle vehicle3 = new Vehicle();
            vehicleRepository.save(vehicle3);

            List<Vehicle> vehicles = vehicleRepository.findAll();
            assertEquals(3, vehicles.size());

        }

        @Test
        public void testToDeleteVehiclesById() {
            vehicleRepository.save(vehicle);
            Vehicle vehicle2 = new Vehicle();
            vehicleRepository.save(vehicle2);
            Vehicle vehicle3 = new Vehicle();
            vehicleRepository.save(vehicle3);

            vehicleRepository.deleteById(2);
            assertEquals(2, vehicleRepository.count());
        }

        @Test
        public void testToDeleteAllVehicles() {
            vehicleRepository.save(vehicle);
            Vehicle vehicle2 = new Vehicle();
            vehicleRepository.save(vehicle2);
            Vehicle vehicle3 = new Vehicle();
            vehicleRepository.save(vehicle3);

            vehicleRepository.deleteAll();
            assertEquals(0, vehicleRepository.count());
        }

        @Test
        public void testThatAVehicleExistsById() {
            vehicleRepository.save(vehicle);
            Vehicle vehicle2 = new Vehicle();
            vehicleRepository.save(vehicle2);
            Vehicle vehicle3 = new Vehicle();
            vehicleRepository.save(vehicle3);

            boolean foundVehicle = vehicleRepository.existsById(2);
            assertTrue(foundVehicle);

        }


    }

