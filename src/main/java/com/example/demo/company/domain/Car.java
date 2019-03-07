package com.example.demo.company.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Embeddable
public class Car {
    @Id
    private long id;

    private String plate;
    private String type;

    public Car() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    @PreUpdate
    @PrePersist
    void beforeChangeHandler() {

    }
}
