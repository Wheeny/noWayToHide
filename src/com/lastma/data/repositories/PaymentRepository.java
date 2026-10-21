package com.lastma.data.repositories;

import com.lastma.data.models.Payment;
import java.util.List;

    public interface PaymentRepository {

        Payment save(Payment payment);

        public long count();

        List<Payment> findByTicketId(int ticketId);

        Payment findByPaymentReference(String reference);

        void deleteAll();

    }

