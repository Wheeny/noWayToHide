package com.lastma.data.repositories;

import com.lastma.data.models.Driver;
import com.lastma.data.models.Officer;

import java.util.List;

public interface DriverRepository {
    Driver save(Driver driver);

    long count();

    Driver findById(int id);

    public Driver findByEmail(String email);

    List<Driver> findAll();

    void deleteById(int driverId);

    void deleteAll();

    boolean existsById(int driverId);

}
