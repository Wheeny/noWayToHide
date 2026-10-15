package com.lastma.data.repositories;

import com.lastma.data.models.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverRepositoryImpl implements DriverRepository {
    private List<Driver> drivers = new ArrayList<Driver>();
    private int driverCount = 0;

    @Override
    public Driver save(Driver driver) {
        drivers.add(driver);
        driver.setId(++driverCount);
        return driver;
    }

    @Override
    public long count() {
        return driverCount;
    }

    @Override
    public Driver findById(int id) {
        for(int counter = 0; counter < drivers.size(); counter++) {
            if(drivers.get(counter).getId() == id) {
                return drivers.get(counter);
            }
        }
        return null;
    }

    @Override
    public List<Driver> findAll() {

        return drivers;
    }

    @Override
    public void deleteById(int driverId) {
        for(int counter = 0; counter < drivers.size(); counter++) {
            if(drivers.get(counter).getId() == driverId) {
                drivers.remove(counter);
                driverCount--;
                break;
            }
        }

    }

    @Override
    public void deleteAll() {
        drivers.clear();
        driverCount = 0;
    }

    @Override
    public boolean existsById(int driverId) {

        if(findById(driverId).getId() == driverId) {
            return true;
        }
        return false;
    };
}
