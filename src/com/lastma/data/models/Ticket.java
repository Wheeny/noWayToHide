package com.lastma.data.models;

import java.util.Date;

public class Ticket {
    private int id;
    private int vehicleId;
    private ViolationType violationType;
    private int violationFee;
    private Date issuedDate;
    private Date dueDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() { return vehicleId; }

    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public ViolationType getViolationType() {
        return violationType;
    }

    public void setViolationType(ViolationType violationType) {
        this.violationType = violationType;
    }

    public int getViolationFee() {
        return violationFee;
    }

    public void setViolationFee(int violationFee) {
        this.violationFee = violationFee;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
