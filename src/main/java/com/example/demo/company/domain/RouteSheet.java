package com.example.demo.company.domain;

import com.example.demo.company.domain.mappers.CarMapper;
import com.example.demo.company.domain.mappers.CompanyMapper;
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

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Embedded
    private AuditId carAuditId = new AuditId();

    @Embedded
    private CarSnapshot carSnapshot = new CarSnapshot();

    @Embedded
    private CompanySnapshot carCompanySnapshot = new CompanySnapshot();


    @ManyToOne
    @JoinColumn(name = "driver_id",
            foreignKey = @ForeignKey(
                    name = "FK_ROUTE_SHEET_CAR",
                    foreignKeyDefinition = "FOREIGN KEY (driver_id) REFERENCES driver ON DELETE SET NULL"
            ))
    private Driver driver;


    @RestResource(exported = false)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "driver_audit_id", referencedColumnName = "id"),
            @JoinColumn(name = "driver_audit_rev", referencedColumnName = "rev"),
    })
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private DriverAudit driverAudit;

    @Embedded
    private LastModifiedAuditId driverLastModifiedAuditId = new LastModifiedAuditId();

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


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CarSnapshot getCarSnapshot() {
        return carSnapshot;
    }

    public CompanySnapshot getCarCompanySnapshot() {
        return carCompanySnapshot;
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
        Optional.ofNullable(this.driver).ifPresent(driver -> {
            this.driverLastModifiedAuditId = new LastModifiedAuditId(driver.getId(), driver.getLastModifiedAt());
        });
        Optional.ofNullable(this.car).ifPresent(car -> {
            this.carSnapshot = CarMapper.INSTANCE.carToCarSnapshot(car);
            Optional.ofNullable(car.getCompany()).ifPresent(company -> {
                this.carCompanySnapshot = CompanyMapper.INSTANCE.companyToCompanySnapshot(company);
            });
        });
    }

}
