package com.lastma.services;

import com.lastma.dtos.requests.PayTicketRequest;
import com.lastma.dtos.responses.PaymentResponse;

public interface PaymentService {

    PaymentResponse payTicket(PayTicketRequest request);

}


