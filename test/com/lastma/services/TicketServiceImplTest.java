package com.lastma.services;

import com.lastma.data.models.Vehicle;
import com.lastma.data.models.ViolationType;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.data.repositories.TicketRepositoryImpl;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.data.repositories.VehicleRepositoryImpl;
import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.responses.TicketResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceImplTest {

    private TicketRepository ticketRepository = new TicketRepositoryImpl();
    private VehicleRepository vehicleRepository = new VehicleRepositoryImpl();
    private TicketService ticketService;
    private IssueTicketRequest issueTicketRequest;

    @BeforeEach
    public void setUp() {
        ticketService = new TicketServiceImpl();
        issueTicketRequest = new IssueTicketRequest();
        ticketRepository.deleteAll();
        vehicleRepository.deleteAll();
    }

    @Test
    public void testThatTicketCanBeIssuedForExistingVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("LSR-555-ZZ");
        vehicle.setMake("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setColour("Silver");
        vehicleRepository.save(vehicle);

        assertEquals(0, ticketRepository.count());

        issueTicketRequest.setVehicleId(vehicle.getId());
        issueTicketRequest.setViolationType(ViolationType.OVERSPEEDING);

        TicketResponse response = ticketService.issueTicket(issueTicketRequest);

        assertEquals(1, ticketRepository.count());
        assertNotNull(response);
        assertEquals(vehicle.getId(), response.getVehicleId());
        assertEquals(ViolationType.OVERSPEEDING, response.getViolationType());
        assertEquals(50000, response.getViolationFee());
        assertNotNull(response.getIssuedDate());
    }

    @Test
    public void testThatIssuingTicketFailsIfVehicleDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            issueTicketRequest.setVehicleId(102);
            issueTicketRequest.setViolationType(ViolationType.ONE_WAY);

            ticketService.issueTicket(issueTicketRequest);
        });
    }

    @Test
    public void testThatTicketsCanBeFoundByVehicleId() {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("ABC-123-XY");
        vehicle.setMake("Honda");
        vehicle.setModel("Civic");
        vehicle.setColour("Black");
        vehicleRepository.save(vehicle);

        issueTicketRequest.setVehicleId(vehicle.getId());
        issueTicketRequest.setViolationType(ViolationType.DUI);
        ticketService.issueTicket(issueTicketRequest);

        IssueTicketRequest request2 = new IssueTicketRequest();
        request2.setVehicleId(vehicle.getId());
        request2.setViolationType(ViolationType.EXPIRED_PAPERS);
        ticketService.issueTicket(request2);

        List<TicketResponse> vehicleTickets = ticketService.findTicketsByVehicleId(vehicle.getId());

        assertEquals(2, vehicleTickets.size());
    }
}