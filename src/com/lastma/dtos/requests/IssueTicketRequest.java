package com.lastma.dtos.requests;

import com.lastma.data.models.ViolationType;

public class IssueTicketRequest {
    private int vehicleId;
    private ViolationType violationType;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public ViolationType getViolationType() {
        return violationType;
    }

    public void setViolationType(ViolationType violationType) {
        this.violationType = violationType;
    }
}