package com.lastma.dtos.requests;

    public class PayTicketRequest {
        private int ticketId;
        private String paymentReference;
        private double amount;

        public int getTicketId() {
            return ticketId;
        }

        public void setTicketId(int ticketId) {
            this.ticketId = ticketId;
        }

        public String getPaymentReference() {
            return paymentReference;
        }

        public void setPaymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }


