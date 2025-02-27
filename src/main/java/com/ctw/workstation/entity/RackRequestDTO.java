package com.ctw.workstation.entity;

import java.util.Date;

public class RackRequestDTO {
    public String serialNumber;
    public Date assembledAt;
    public String defaultLocation;
    public String rackStatus;

    public RackRequestDTO(String serialNumber, Date assembledAt, String defaultLocation, String rackStatus) {
        this.serialNumber = serialNumber;
        this.assembledAt = assembledAt;
        this.defaultLocation = defaultLocation;
        this.rackStatus = rackStatus;
    }
}

