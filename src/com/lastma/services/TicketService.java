package com.lastma.services;

import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.responses.TicketResponse;
import java.util.List;

    public interface TicketService {

        TicketResponse issueTicket(IssueTicketRequest request);

        List<TicketResponse> findTicketsByVehicleId(int vehicleId);
    }


