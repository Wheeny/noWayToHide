package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.dtos.requests.FindDriverRequest;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.responses.FindDriverResponse;
import com.lastma.dtos.responses.RegisterDriverResponse;

import java.util.List;

public interface DriverService {

    RegisterDriverResponse registerDriver(RegisterDriverRequest request);

    FindDriverResponse findDriverById(FindDriverRequest request);

    List<Driver> findAllDrivers();

    }


