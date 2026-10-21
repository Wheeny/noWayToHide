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
            return officerService.register(request);
        } catch (IllegalArgumentException error) {
            System.out.println("Registration Error: " + error.getMessage());
            return null;
        }
    }

    private OfficerLoginResponse officerLoginController(OfficerLoginRequest loginRequest){
        OfficerLoginResponse response = null;
        try {
            return officerService.login(loginRequest);
        } catch (IllegalArgumentException error) {
            System.out.println("Login Error: " + error.getMessage());
            return null;
        }
    }

    private FindOfficerResponse officerFindOfficerById(FindOfficerRequest findOfficerRequest) {
        try {
            return officerService.findOfficerById(findOfficerRequest);
        } catch (IllegalArgumentException error) {
            System.out.println("Lookup Error: " + error.getMessage());
            return null;
        }
    }

}
