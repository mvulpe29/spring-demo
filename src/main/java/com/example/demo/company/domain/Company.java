package com.example.demo.company.domain;

import com.example.demo.BaseEntityInterface;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Audited
public class Company implements BaseEntityInterface {

    @Id
    private long id;

    private String owner;
    private String name;
    private String registryNo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    public Company() {
    }

    public Company(String owner, String name, String registryNo) {
        this.owner = owner;
        this.name = name;
        this.registryNo = registryNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistryNo() {
        return registryNo;
    }

    public void setRegistryNo(String registryNo) {
        this.registryNo = registryNo;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }


    @PrePersist
    @PreUpdate
    public void updateInvoices() {

    }

}