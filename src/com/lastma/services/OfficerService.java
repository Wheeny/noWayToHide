package com.lastma.services;

import com.lastma.data.models.Officer;
import com.lastma.dtos.requests.FindOfficerRequest;
import com.lastma.dtos.requests.OfficerLoginRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.responses.FindOfficerResponse;
import com.lastma.dtos.responses.OfficerLoginResponse;
import com.lastma.dtos.responses.RegisterOfficerResponse;

public interface OfficerService {

    RegisterOfficerResponse register(RegisterOfficerRequest request);

    OfficerLoginResponse login(OfficerLoginRequest loginRequest);

    FindOfficerResponse findOfficerById(FindOfficerRequest findOfficerRequest);

}
