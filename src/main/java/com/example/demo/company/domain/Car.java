package com.example.demo.company.domain;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity
@Audited
public class Car extends BaseEntity {

    private String plate;
    private String type;

    public Car() {
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
