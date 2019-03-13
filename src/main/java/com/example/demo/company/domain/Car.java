package com.example.demo.company.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
public class Car extends CarData {
    @Id
    @Column(name = "id")
    private long id;


    private long version;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company company;

    public Car() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
