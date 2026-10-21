package com.lastma.data.repositories;

import com.lastma.data.models.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    class PaymentRepositoryImplTest {

        private PaymentRepositoryImpl paymentRepository;
        private Payment payment;

        @BeforeEach
        public void setUp() {
            paymentRepository = new PaymentRepositoryImpl();
            paymentRepository.deleteAll();

            payment = new Payment();
            payment.setTicketId(1);
            payment.setPaymentReference("REF-12345");
            payment.setAmount(50000.0);
            payment.setPaymentDate(new Date());
        }

        @Test
        public void testThatPaymentCanBeSavedAndCountIncreases() {
            assertEquals(0, paymentRepository.count());

            paymentRepository.save(payment);

            assertEquals(1, paymentRepository.count());
            assertEquals(1, payment.getId());
        }

        @Test
        public void testThatMultiplePaymentsCanBeSaved() {
            paymentRepository.save(payment);

            Payment payment2 = new Payment();
            payment2.setTicketId(2);
            payment2.setPaymentReference("REF-67890");
            payment2.setAmount(25000.0);
            payment2.setPaymentDate(new Date());

            paymentRepository.save(payment2);

            assertEquals(2, paymentRepository.count());
        }

        @Test
        public void testFindByTicketId() {
            paymentRepository.save(payment);

            Payment payment2 = new Payment();
            payment2.setTicketId(1); // Same ticket ID
            payment2.setPaymentReference("REF-99999");
            payment2.setAmount(10000.0);
            payment2.setPaymentDate(new Date());
            paymentRepository.save(payment2);

            Payment payment3 = new Payment();
            payment3.setTicketId(2); // Different ticket ID
            payment3.setPaymentReference("REF-88888");
            payment3.setAmount(15000.0);
            payment3.setPaymentDate(new Date());
            paymentRepository.save(payment3);

            List<Payment> ticket1Payments = paymentRepository.findByTicketId(1);
            assertEquals(2, ticket1Payments.size());

            List<Payment> ticket2Payments = paymentRepository.findByTicketId(2);
            assertEquals(1, ticket2Payments.size());

            List<Payment> nonExistentTicketPayments = paymentRepository.findByTicketId(99);
            assertTrue(nonExistentTicketPayments.isEmpty());
        }

        @Test
        public void testFindByPaymentReferenceCaseInsensitive() {
            paymentRepository.save(payment); // Reference is "REF-12345"

            Payment foundByExact = paymentRepository.findByPaymentReference("REF-12345");
            assertNotNull(foundByExact);
            assertEquals(1, foundByExact.getId());

            Payment foundByLower = paymentRepository.findByPaymentReference("ref-12345");
            assertNotNull(foundByLower);
            assertEquals("REF-12345", foundByLower.getPaymentReference());
        }

        @Test
        public void testFindByPaymentReferenceNotFound() {
            paymentRepository.save(payment);

            Payment found = paymentRepository.findByPaymentReference("UNKNOWN-REF");
            assertNull(found);
        }

        @Test
        public void testDeleteAll() {
            paymentRepository.save(payment);
            assertEquals(1, paymentRepository.count());

            paymentRepository.deleteAll();
            assertEquals(0, paymentRepository.count());
        }
    }


