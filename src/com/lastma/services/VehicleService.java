package com.lastma.services;

import com.lastma.data.models.Vehicle;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.responses.RegisterVehicleResponse;
import com.lastma.dtos.responses.VehicleResponse;

import java.util.List;

    public interface VehicleService {

        RegisterVehicleResponse registerVehicle(RegisterVehicleRequest registerVehicleRequest);

        VehicleResponse findVehicleByRegistrationNumber(String regNum);

        List<VehicleResponse>findVehiclesByDriverId(int motoristId);
    }

