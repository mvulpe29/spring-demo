package com.example.demo.addresses;

import javax.persistence.Embeddable;

@Embeddable
public class LifeEvent {

    private String year;

    private String description;

    public LifeEvent() {
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}