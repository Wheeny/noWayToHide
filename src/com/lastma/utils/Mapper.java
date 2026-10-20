package com.lastma.utils;

import com.lastma.data.models.Officer;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.responses.OfficerLoginResponse;

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
}
