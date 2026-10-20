package com.lastma.utils;

import com.lastma.data.models.Officer;
import com.lastma.data.models.Vehicle;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.responses.FindOfficerResponse;
import com.lastma.dtos.responses.OfficerLoginResponse;
import com.lastma.dtos.responses.RegisterVehicleResponse;

public class Mapper {

    public static Officer mapToRegisterOfficer(RegisterOfficerRequest request) {
        Officer officer = new Officer();
        officer.setFullName(request.getFullName());
        officer.setEmail(request.getEmail());
        officer.setPassword(request.getPassword());
        return officer;
    }

    public static OfficerLoginResponse MapToLoginOfficerResponse(Officer foundOfficer) {
        OfficerLoginResponse response = new OfficerLoginResponse();
        response.setEmail(foundOfficer.getEmail());
        response.setFullName(foundOfficer.getFullName());
        return response;
    }

    public static FindOfficerResponse MapToFindOfficerResponse(Officer foundOfficer) {
        FindOfficerResponse response = new FindOfficerResponse();
        response.setId(foundOfficer.getId());
        response.setFullName(foundOfficer.getFullName());
        return response;
    }

    public static RegisterVehicleResponse MapToRegisterVehicle(RegisterVehicleRequest registerVehicleRequest) {

        RegisterVehicleResponse vehicle = new RegisterVehicleResponse();
        vehicle.setRegistrationNumber(registerVehicleRequest.getRegistrationNumber());
        vehicle.setMake(registerVehicleRequest.getMake());
        vehicle.setModel(registerVehicleRequest.getModel());
        vehicle.setColour(registerVehicleRequest.getColour());
        vehicle.setDriverId(registerVehicleRequest.getDriverId());
            return vehicle;
    }
}
