package com.example.demo.company.domain;

import com.example.demo.envers.AuditId;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Audited
public class RouteSheet {
    @Id
    private long id;
    private Instant date;
    private String description;
    private String label;

    @RestResource(exported = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "car_audit_id")),
            @AttributeOverride(name = "rev", column = @Column(name = "car_audit_rev"))
    })
    private AuditId carAuditId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car carMutable;


    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;


    @RestResource(exported = false)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "driver_audit_id", referencedColumnName = "id"),
            @JoinColumn(name = "driver_audit_rev", referencedColumnName = "rev")
    })
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DriverAudit driverAudit;

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


    public AuditId getCarAuditId() {
        return carAuditId;
    }

    public void setCarAuditId(AuditId carAuditId) {
        this.carAuditId = carAuditId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DriverAudit getDriverAudit() {
        return driverAudit;
    }

    public void setDriverAudit(DriverAudit driverAudit) {
        this.driverAudit = driverAudit;
    }

    @PrePersist
    @PreUpdate
    private void preUpdate() {

    }

}
