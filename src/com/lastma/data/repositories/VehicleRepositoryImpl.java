package com.lastma.data.repositories;

import com.lastma.data.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryImpl implements VehicleRepository {
  
        private List<Vehicle> vehicles = new ArrayList<Vehicle>();
        private int vehicleCount = 0;

        @Override
        public Vehicle save(Vehicle vehicle) {
            vehicles.add(vehicle);
            vehicle.setId(++vehicleCount);
            return vehicle;
        }

        @Override
        public long count() {
            return vehicleCount;
        }

        @Override
        public Vehicle findById(int id) {
            for(int counter = 0; counter < vehicles.size(); counter++) {
                if(vehicles.get(counter).getId() == id) {
                    return vehicles.get(counter);
                }
            }
            return null;
        }

        @Override
        public List<Vehicle> findAll() {

            return vehicles;
        }

        @Override
        public void deleteById(int vehicleId) {
            for(int counter = 0; counter < vehicles.size(); counter++) {
                if(vehicles.get(counter).getId() == vehicleId) {
                    vehicles.remove(counter);
                    vehicleCount--;
                    break;
                }
            }

        }

        @Override
        public void deleteAll() {
            vehicles.clear();
            vehicleCount = 0;
        }

        @Override
        public boolean existsById(int vehicleId) {

            if(findById(vehicleId).getId() == vehicleId) {
                return true;
            }
            return false;
        };
    }


