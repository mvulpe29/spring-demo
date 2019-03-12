package com.example.demo.company.domain;

import com.example.demo.envers.AuditId;
import com.example.demo.envers.LastModifiedAuditId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;


@Entity
@Audited
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "driver_audit_lm_id")),
            @AttributeOverride(name = "lastModifiedAt", column = @Column(name = "driver_audit_lm_lastModifiedAt"))
    })
    private LastModifiedAuditId driverLastModifiedAuditId;

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

    public LastModifiedAuditId getDriverLastModifiedAuditId() {
        return driverLastModifiedAuditId;
    }

    public void setDriverLastModifiedAuditId(LastModifiedAuditId driverLastModifiedAuditId) {
        this.driverLastModifiedAuditId = driverLastModifiedAuditId;
    }

    @PrePersist
    @PreUpdate
    private void preUpdate() {
        Optional.ofNullable(this.getDriver()).ifPresent(driver -> {
            this.driverLastModifiedAuditId = new LastModifiedAuditId(driver.getId(), driver.getLastModifiedAt());
        });
    }

}
