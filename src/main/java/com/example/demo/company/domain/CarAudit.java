package com.example.demo.company.domain;

import org.hibernate.annotations.Subselect;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Audited(targetAuditMode = NOT_AUDITED)
@Immutable
@Subselect(
        "SELECT CAR_AUD.*, REVINFO.REVTSTMP FROM CAR_AUD join REVINFO on CAR_AUD.REV=REVINFO.REV"
)
public class CarAudit implements Serializable {

    @EmbeddedId
    private AuditId auditId;
    private Car car;


    @Column(insertable = false, updatable = false)
    private long id;

    @Column(insertable = false, updatable = false)
    private long rev;


    public CarAudit() {
    }

    public AuditId getAuditId() {
        return auditId;
    }

    public void setAuditId(AuditId auditId) {
        this.auditId = auditId;
    }

    public long getId() {
        return id;
    }

    public long getRev() {
        return rev;
    }


    public Car getCar() {
        return car;
    }

}
