package com.example.demo.company.domain;

import com.example.demo.common.Auditable;
import org.hibernate.envers.Audited;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Audited
public class CompanyData extends Auditable {

    private String owner;
    private String name;
    private String registryNo;

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


}