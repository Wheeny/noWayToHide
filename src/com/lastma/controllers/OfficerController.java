package com.lastma.controllers;

import com.lastma.dtos.requests.FindOfficerRequest;
import com.lastma.dtos.requests.OfficerLoginRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.responses.FindOfficerResponse;
import com.lastma.dtos.responses.OfficerLoginResponse;
import com.lastma.dtos.responses.RegisterOfficerResponse;
import com.lastma.services.OfficerService;
import com.lastma.services.OfficerServiceImpl;

public class OfficerController {

    private OfficerServiceImpl officerService = new OfficerServiceImpl();

    public RegisterOfficerResponse officerRegisterController(RegisterOfficerRequest request) {
        RegisterOfficerResponse response = null;
        try {
            response = officerService.register(request);
        } catch (IllegalArgumentException error) {
            System.out.println("Registration failed: " + error.getMessage());
        }
        return response;
    }

    private OfficerLoginResponse officerLoginController(OfficerLoginRequest loginRequest){
        OfficerLoginResponse response = null;
        try {
            response = officerService.login(loginRequest);
        }
        catch (IllegalArgumentException error) {
            System.out.println("Login failed: " + error.getMessage());
        }
        return response;
    }

    private FindOfficerResponse officerFindOfficerById(FindOfficerRequest findOfficerRequest){
        FindOfficerResponse response = null;
        try {
            response = officerService.findOfficerById(findOfficerRequest);
        }
        catch (IllegalArgumentException error) {
            System.out.println("Find officer failed: " + error.getMessage());
        }
        return response;
    }

}
