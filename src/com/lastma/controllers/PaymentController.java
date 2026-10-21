package com.lastma.controllers;

import com.lastma.dtos.requests.PayTicketRequest;
import com.lastma.dtos.responses.PaymentResponse;
import com.lastma.services.PaymentService;
import com.lastma.services.PaymentServiceImpl;

public class PaymentController {

    private PaymentService paymentService = new PaymentServiceImpl();

    public PaymentResponse payTicketController(PayTicketRequest request) {
        try {
            return paymentService.payTicket(request);
        } catch (IllegalArgumentException error) {
            System.out.println("Payment Error: " + error.getMessage());
            return null;
        }
    }
}