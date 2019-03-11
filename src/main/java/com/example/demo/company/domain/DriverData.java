package com.example.demo.company.domain;

import com.example.demo.common.Auditable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Audited
public class DriverData extends Auditable {
    @Id
    @Column(name = "id", updatable = false, insertable = false)
    private Long id;

    private String name;
    private String licenseCategory;

    public DriverData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

}
