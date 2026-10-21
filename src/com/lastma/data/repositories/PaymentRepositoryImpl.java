package com.lastma.data.repositories;

import com.lastma.data.models.Payment;
import java.util.ArrayList;
import java.util.List;

    public class PaymentRepositoryImpl implements PaymentRepository {
        private static List<Payment> payments = new ArrayList<>();
        private static int paymentCount = 0;

        @Override
        public Payment save(Payment payment) {
            payments.add(payment);
            payment.setId(++paymentCount);
            return payment;
        }

        @Override
        public long count() {
            return paymentCount;
        }

        @Override
        public List<Payment> findByTicketId(int ticketId) {
            List<Payment> result = new ArrayList<>();
            for (int count = 0; count < payments.size(); count++) {
                if (payments.get(count).getTicketId() == ticketId) {
                    result.add(payments.get(count));
                }
            }
            return result;
        }

        @Override
        public Payment findByPaymentReference(String reference) {
            for (int count = 0; count < payments.size(); count++) {
                if (payments.get(count).getPaymentReference().equalsIgnoreCase(reference)) {
                    return payments.get(count);
                }
            }
            return null;
        }

        @Override
        public void deleteAll() {
            payments.clear();
            paymentCount = 0;

    }


}
