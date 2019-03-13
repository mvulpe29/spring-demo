package com.example.demo.company.domain;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;


@Entity
@Immutable
//language=SQL
@Subselect(
        "SELECT route_sheet.id          as  id," +
        "       route_sheet.date        as  date," +
        "       route_sheet.label       as  label," +
        "       route_sheet.description as  description," +
        "       car_aud.plate           as  car_plate," +
        "       car_aud.type            as  car_type," +
        "       company_aud.name            car_company_name," +
        "       driver_aud.license_category driver_license_category," +
        "       employee_aud.first_name     driver_first_name," +
        "       employee_aud.last_name      driver_last_name " +
        "FROM route_sheet" +
        "       LEFT JOIN car_aud" +
        "                 ON route_sheet.car_audit_id_id = car_aud.id" +
        "                   AND route_sheet.car_audit_id_rev = car_aud.rev" +
        "       LEFT JOIN company_aud" +
        "                 ON car_aud.company_id = company_aud.id" +
        "                   AND company_aud.rev = (SELECT Max(company_aud.rev)" +
        "                                          FROM company_aud" +
        "                                          WHERE company_aud.id = car_aud.company_id" +
        "                                            AND company_aud.rev <= car_aud.rev)" +
        "       LEFT JOIN driver_aud" +
        "                 ON route_sheet.driver_last_modified_audit_id_id = driver_aud.id" +
        "                   AND driver_aud.rev = (SELECT Max(driver_aud.rev)" +
        "                                         FROM driver_aud" +
        "                                         WHERE driver_aud.id = route_sheet.driver_last_modified_audit_id_id" +
        "                                           AND driver_aud.last_modified_at =" +
        "                                               route_sheet.driver_last_modified_audit_id_last_modified_at)" +
        "       LEFT JOIN employee_aud" +
        "                 ON employee_aud.id = driver_aud.id" +
        "                   AND employee_aud.rev = (SELECT Max(employee_aud.rev)" +
        "                                           FROM employee_aud" +
        "                                           WHERE employee_aud.id = driver_aud.employee_id" +
        "                                             AND employee_aud.rev <= driver_aud.rev)"
)
public class RouteSheetExcerpt {
    @Id
    private long id;
    private Instant date;
    private String description;
    private String label;
    private String carPlate;
    private String carType;
    private String carCompanyName;
    private String driverLicenseCategory;
    private String driverFirstName;
    private String driverLastName;


    public RouteSheetExcerpt() {
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

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarCompanyName() {
        return carCompanyName;
    }

    public void setCarCompanyName(String carCompanyName) {
        this.carCompanyName = carCompanyName;
    }

    public String getDriverLicenseCategory() {
        return driverLicenseCategory;
    }

    public void setDriverLicenseCategory(String driverLicenseCategory) {
        this.driverLicenseCategory = driverLicenseCategory;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }
}
