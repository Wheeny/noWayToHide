package com.lastma.services;

import com.lastma.data.models.Officer;
import com.lastma.dtos.requests.OfficerLoginRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.responses.OfficerLoginResponse;

public interface OfficerService {

    void register(RegisterOfficerRequest request);

    OfficerLoginResponse login(OfficerLoginRequest loginRequest);

    Officer findOfficerById(int id);
}
