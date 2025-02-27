package com.ctw.workstation.entity;

public class TeamRequestDTO {
    public String product;
    public String name;
    public String defaultLocation;

    public TeamRequestDTO(String product, String name, String defaultLocation) {
        this.product = product;
        this.name = name;
        this.defaultLocation = defaultLocation;
    }
}



