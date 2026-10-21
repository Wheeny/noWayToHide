package com.lastma.utils;

import com.lastma.data.models.*;
import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.responses.*;

import java.util.Date;

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

    public static Ticket mapToIssueTicket(IssueTicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setVehicleId(request.getVehicleId());
        ticket.setViolationType(request.getViolationType());
        ticket.setViolationFee(request.getViolationType().getFee()); // Automatically sets fee from enum!
        ticket.setIssuedDate(new Date());
        return ticket;
    }

    public static TicketResponse mapToTicketResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setId(ticket.getId());
        response.setVehicleId(ticket.getVehicleId());
        response.setViolationType(ticket.getViolationType());
        response.setViolationFee(ticket.getViolationFee());
        response.setIssuedDate(ticket.getIssuedDate());
        return response;
    }


    public static PaymentResponse mapToPaymentResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());
        response.setTicketId(payment.getTicketId());
        response.setPaymentReference(payment.getPaymentReference());
        response.setAmount(payment.getAmount());
        response.setPaymentDate(payment.getPaymentDate());
        return response;
    }

    public static Driver mapToRegisterDriver(RegisterDriverRequest request) {
        Driver driver = new Driver();
        driver.setFullName(request.getFullName());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setEmail(request.getEmail());
        return driver;
    }

    public static RegisterDriverResponse mapToRegisterDriverResponse(Driver driver) {
        RegisterDriverResponse response = new RegisterDriverResponse();
        response.setId(driver.getId());
        response.setFullName(driver.getFullName());
        response.setPhoneNumber(driver.getPhoneNumber());
        response.setEmail(driver.getEmail());
        return response;
    }

    public static FindDriverResponse mapToFindDriverResponse(Driver driver) {
        FindDriverResponse response = new FindDriverResponse();
        response.setId(driver.getId());
        response.setFullName(driver.getFullName());
        response.setPhoneNumber(driver.getPhoneNumber());
        response.setEmail(driver.getEmail());
        return response;
    }


    public static Vehicle mapToRegisterVehicle(RegisterVehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(request.getRegistrationNumber());
        vehicle.setMake(request.getMake());
        vehicle.setModel(request.getModel());
        vehicle.setColour(request.getColour());
        vehicle.setDriverId(request.getDriverId());
        return vehicle;
    }

    public static RegisterVehicleResponse mapToRegisterVehicleResponse(Vehicle vehicle) {
        RegisterVehicleResponse response = new RegisterVehicleResponse();
        response.setId(vehicle.getId());
        response.setRegistrationNumber(vehicle.getRegistrationNumber());
        response.setMake(vehicle.getMake());
        response.setModel(vehicle.getModel());
        response.setColour(vehicle.getColour());
        response.setDriverId(vehicle.getDriverId());
        return response;
    }

    public static VehicleResponse mapToVehicleResponse(Vehicle vehicle) {
        VehicleResponse response = new VehicleResponse();
        response.setId(vehicle.getId());
        response.setRegistrationNumber(vehicle.getRegistrationNumber());
        response.setMake(vehicle.getMake());
        response.setModel(vehicle.getModel());
        response.setColour(vehicle.getColour());
        response.setDriverId(vehicle.getDriverId());
        return response;
    }

}
