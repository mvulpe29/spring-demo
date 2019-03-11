package com.example.demo.company.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
@Audited
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Driver extends DriverData {
    @Version
    @Column(name = "version")
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
