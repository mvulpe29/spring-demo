package com.example.demo.company;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Audited
public class RouteSheet {
    @Id
    private long id;
    private LocalDate date;
    private String description;
    private String label;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "car_audit_id", referencedColumnName = "id"),
            @JoinColumn(name = "car_audit_rev", referencedColumnName = "rev")
    })
    CarAudit carAudit;

    public RouteSheet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CarAudit getCarAudit() {
        return carAudit;
    }

    public void setCarAudit(CarAudit carAudit) {
        this.carAudit = carAudit;
    }
}
