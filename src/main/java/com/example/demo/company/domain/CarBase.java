package com.example.demo.company.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CarBase {

    private String plate;
    private String type;


    public CarBase() {
    }

    public CarBase(String plate, String type) {
        this.plate = plate;
        this.type = type;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
