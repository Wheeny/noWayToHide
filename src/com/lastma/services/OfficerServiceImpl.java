package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.models.Officer;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import com.lastma.data.repositories.OfficerRepository;
import com.lastma.data.repositories.OfficerRepositoryImpl;
import com.lastma.dtos.requests.FindOfficerRequest;
import com.lastma.dtos.requests.OfficerLoginRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.responses.OfficerLoginResponse;
import com.lastma.dtos.responses.FindOfficerResponse;
import com.lastma.dtos.responses.RegisterOfficerResponse;
import com.lastma.utils.Mapper;

public class OfficerServiceImpl implements OfficerService {

   private OfficerRepository officerRepo = new OfficerRepositoryImpl();

    @Override
    public RegisterOfficerResponse register(RegisterOfficerRequest request) {
         Officer mappedOfficer = Mapper.mapToRegisterOfficer(request);
         officerRepo.save(mappedOfficer);

         RegisterOfficerResponse response = new RegisterOfficerResponse();

           response.setEmail(request.getEmail());
           response.setFullName(request.getFullName());
           return response;
    }

//    private Officer findOfficerOrThrow(String email) {
//        Officer foundOfficer = officerRepo.findByEmail(email);
//        if (foundOfficer == null) {
//            throw new IllegalArgumentException("Officer with email " + email + " does not exist");
//        }
//        return foundOfficer;
//    }

    @Override
    public OfficerLoginResponse login(OfficerLoginRequest loginRequest) {

        Officer foundOfficer = officerRepo.findByEmail(loginRequest.getEmail());
        if (foundOfficer == null) {
            throw new IllegalArgumentException("Officer with email " + loginRequest.getEmail() + " does not exist");
        }
        if (foundOfficer.getPassword().equals(loginRequest.getPassword())) {
            foundOfficer.setLoggedIn(true);
            return Mapper.MapToLoginOfficerResponse(foundOfficer);
        }
        throw new IllegalArgumentException("Invalid email or password");
    }


    @Override
    public FindOfficerResponse findOfficerById(FindOfficerRequest findOfficerRequest ) {
        Officer foundOfficer = officerRepo.findById(findOfficerRequest.getId());
        if (foundOfficer == null) {
            throw new IllegalArgumentException("Officer with id " + findOfficerRequest.getId() + " does not exist");
        }

        return Mapper.MapToFindOfficerResponse(foundOfficer);
    }


}
