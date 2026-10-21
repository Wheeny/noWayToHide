package com.lastma.controllers;

import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.responses.TicketResponse;
import com.lastma.services.TicketService;
import com.lastma.services.TicketServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class TicketController {

    private TicketService ticketService = new TicketServiceImpl();

    public TicketResponse issueTicketController(IssueTicketRequest request) {
        try {
            return ticketService.issueTicket(request);
        } catch (IllegalArgumentException error) {
            System.out.println("Ticket Issuance Error: " + error.getMessage());
            return null;
        }
    }

    public List<TicketResponse> findTicketsByVehicleIdController(int vehicleId) {
        try {
            return ticketService.findTicketsByVehicleId(vehicleId);
        } catch (Exception error) {
            System.out.println("Error fetching tickets: " + error.getMessage());
            return new ArrayList<>();
        }
    }
}