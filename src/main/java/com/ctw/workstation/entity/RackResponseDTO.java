package com.ctw.workstation.entity;

import java.util.Date;

public class RackResponseDTO {
    public Long id;
    public String serialNumber;
    public Date assembledAt;
    public String defaultLocation;
    public String rackStatus;

    public RackResponseDTO(Long id, String serialNumber, Date assembledAt, String defaultLocation, String rackStatus) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.assembledAt = assembledAt;
        this.defaultLocation = defaultLocation;
        this.rackStatus = rackStatus;
    }
}

