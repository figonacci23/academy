package com.ctw.workstation.entity;

public class TeamResponseDTO {
    public Long id;
    public String product;
    public String name;
    public String defaultLocation;

    public TeamResponseDTO(Long id, String product, String name, String defaultLocation) {
        this.id = id;
        this.product = product;
        this.name = name;
        this.defaultLocation = defaultLocation;
    }
}


