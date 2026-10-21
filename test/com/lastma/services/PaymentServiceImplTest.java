package com.lastma.services;

import com.lastma.data.models.Ticket;
import com.lastma.data.models.ViolationType;
import com.lastma.data.repositories.PaymentRepository;
import com.lastma.data.repositories.PaymentRepositoryImpl;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.data.repositories.TicketRepositoryImpl;
import com.lastma.dtos.requests.PayTicketRequest;
import com.lastma.dtos.responses.PaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

    public class PaymentServiceImplTest {

        private PaymentRepository paymentRepository = new PaymentRepositoryImpl();
        private TicketRepository ticketRepository = new TicketRepositoryImpl();
        private PaymentService paymentService;
        private PayTicketRequest payTicketRequest;

        @BeforeEach
        public void setUp() {
            paymentService = new PaymentServiceImpl();
            payTicketRequest = new PayTicketRequest();
            paymentRepository.deleteAll();
            ticketRepository.deleteAll();
        }

        @Test
        public void testThatTicketCanBePaidSuccessfully() {
            Ticket ticket = new Ticket();
            ticket.setVehicleId(1);
            ticket.setViolationType(ViolationType.OVERSPEEDING);
            ticket.setViolationFee(50000);
            ticket.setIssuedDate(new Date());
            ticketRepository.save(ticket);

            assertEquals(0, paymentRepository.count());

            payTicketRequest.setTicketId(ticket.getId());
            payTicketRequest.setPaymentReference("PAY-999000");
            payTicketRequest.setAmount(50000.0);

            PaymentResponse response = paymentService.payTicket(payTicketRequest);

            assertEquals(1, paymentRepository.count());
            assertNotNull(response);
            assertEquals(ticket.getId(), response.getTicketId());
            assertEquals("PAY-999000", response.getPaymentReference());
            assertEquals(50000.0, response.getAmount());
            assertNotNull(response.getPaymentDate());
        }

        @Test
        public void testThatPayingNonExistentTicketThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> {
                payTicketRequest.setTicketId(900);
                payTicketRequest.setPaymentReference("PAY-123456");
                payTicketRequest.setAmount(25000.0);

                paymentService.payTicket(payTicketRequest);
            });
        }

        @Test
        public void testThatDuplicatePaymentReferenceThrowsException() {
            Ticket ticket = new Ticket();
            ticket.setVehicleId(1);
            ticket.setViolationType(ViolationType.ONE_WAY);
            ticket.setViolationFee(25000);
            ticket.setIssuedDate(new Date());
            ticketRepository.save(ticket);

            payTicketRequest.setTicketId(ticket.getId());
            payTicketRequest.setPaymentReference("REF-DUPLICATE-01");
            payTicketRequest.setAmount(25000.0);

            paymentService.payTicket(payTicketRequest);

            PayTicketRequest duplicateRequest = new PayTicketRequest();
            duplicateRequest.setTicketId(ticket.getId());
            duplicateRequest.setPaymentReference("REF-DUPLICATE-01");
            duplicateRequest.setAmount(25000.0);

            assertThrows(IllegalArgumentException.class, () -> {
                paymentService.payTicket(duplicateRequest);
            });
        }

        @Test
        public void testThatZeroOrNegativeAmountThrowsException() {
            Ticket ticket = new Ticket();
            ticket.setVehicleId(1);
            ticket.setViolationType(ViolationType.DUI);
            ticket.setViolationFee(11000);
            ticket.setIssuedDate(new Date());
            ticketRepository.save(ticket);

            payTicketRequest.setTicketId(ticket.getId());
            payTicketRequest.setPaymentReference("REF-ZERO-AMT");
            payTicketRequest.setAmount(0.0);

            assertThrows(IllegalArgumentException.class, () -> {
                paymentService.payTicket(payTicketRequest);
            });
        }
    }

