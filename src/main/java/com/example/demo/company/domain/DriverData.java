package com.example.demo.company.domain;

import com.example.demo.common.Auditable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@MappedSuperclass
@Audited
public class DriverData extends Auditable {
    @Id
    @Column(name = "id", updatable = false, insertable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;

    private String licenseCategory;

    public DriverData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

}
