package com.example.demo.company.domain;

import com.example.demo.BaseEntityInterface;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Audited
public class Company extends CompanyData implements BaseEntityInterface {

    @Id
    private long id;

    @Version
    private long version;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    public Company() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
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