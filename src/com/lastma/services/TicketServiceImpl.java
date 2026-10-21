package com.lastma.services;

import com.lastma.data.models.Ticket;
import com.lastma.data.models.Vehicle;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.data.repositories.TicketRepositoryImpl;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.data.repositories.VehicleRepositoryImpl;
import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.responses.TicketResponse;
import com.lastma.utils.Mapper;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepo = new TicketRepositoryImpl();
    private VehicleRepository vehicleRepo = new VehicleRepositoryImpl();

    @Override
    public TicketResponse issueTicket(IssueTicketRequest request) {
        Vehicle vehicle = vehicleRepo.findById(request.getVehicleId());
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle does not exist. Cannot issue ticket.");
        }

        Ticket ticket = Mapper.mapToIssueTicket(request);
        ticketRepo.save(ticket);

        return Mapper.mapToTicketResponse(ticket);
    }

    @Override
    public List<TicketResponse> findTicketsByVehicleId(int vehicleId) {
        List<Ticket> tickets = ticketRepo.findByVehicleId(vehicleId);
        List<TicketResponse> responses = new ArrayList<>();
        for (int count = 0; count < tickets.size(); count++) {
            responses.add(Mapper.mapToTicketResponse(tickets.get(count)));
        }
        return responses;
    }
}