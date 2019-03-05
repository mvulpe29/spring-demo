package com.example.demo.company;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Audited
@AuditOverrides({
        @AuditOverride(forClass = CarBase.class)
})
public class Car extends CarBase {
    @Id
    private long id;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
