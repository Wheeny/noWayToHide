package com.lastma.data.models;

import java.util.Date;

    public class Payment {
        private int id;
        private int ticketId;
        private String paymentReference;
        private double amount;
        private Date paymentDate;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getTicketId() { return ticketId; }
        public void setTicketId(int ticketId) { this.ticketId = ticketId; }

        public String getPaymentReference() { return paymentReference; }
        public void setPaymentReference(String paymentReference) { this.paymentReference = paymentReference; }

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }

        public Date getPaymentDate() { return paymentDate; }
        public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
    }


