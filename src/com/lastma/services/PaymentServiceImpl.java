package com.lastma.services;

import com.lastma.data.models.Payment;
import com.lastma.data.models.Ticket;
import com.lastma.data.repositories.PaymentRepository;
import com.lastma.data.repositories.PaymentRepositoryImpl;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.data.repositories.TicketRepositoryImpl;
import com.lastma.dtos.requests.PayTicketRequest;
import com.lastma.dtos.responses.PaymentResponse;
import com.lastma.utils.Mapper;

import java.util.Date;

public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepo = new PaymentRepositoryImpl();
    private TicketRepository ticketRepo = new TicketRepositoryImpl();

    @Override
    public PaymentResponse payTicket(PayTicketRequest request) {
        Ticket ticket = ticketRepo.findById(request.getTicketId());
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket with id " + request.getTicketId() + " does not exist.");
        }

        Payment existingRef = paymentRepo.findByPaymentReference(request.getPaymentReference());
        if (existingRef != null) {
            throw new IllegalArgumentException("Payment reference already exists.");
        }

        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }

        Payment payment = new Payment();
        payment.setTicketId(request.getTicketId());
        payment.setPaymentReference(request.getPaymentReference());
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(new Date());

        paymentRepo.save(payment);

        return Mapper.mapToPaymentResponse(payment);
    }
}