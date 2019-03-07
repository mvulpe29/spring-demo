package com.example.demo.company.domain;

import org.hibernate.envers.Audited;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;

@Entity
@Audited
public class RouteSheet {
    @RestResource(exported = false)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "car_audit_id", referencedColumnName = "id"),
            @JoinColumn(name = "car_audit_rev", referencedColumnName = "rev")
    })
    CarAudit carImmutable;
    @Id
    private long id;
    private Instant date;
    private String description;
    private String label;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car carMutable;

    public RouteSheet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
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

    public Car getCarMutable() {
        return carMutable;
    }

    public void setCarMutable(Car carMutable) {
        this.carMutable = carMutable;
    }

    public void setCarImmutable(CarAudit carImmutable) {
        this.carImmutable = carImmutable;
    }

    public Car getCar() {
        if (carMutable != null) {
            return carMutable;
        }
        return Optional.ofNullable(carImmutable).map(CarAudit::getCar).orElse(null);
    }

    @PrePersist
    @PreUpdate
    private void addCarAuditToRouteSheet() {

    }

}
