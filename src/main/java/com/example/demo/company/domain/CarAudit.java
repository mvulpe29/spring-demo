package com.example.demo.company.domain;

import com.example.demo.common.AuditTable;
import com.example.demo.envers.AuditId;
import org.hibernate.annotations.Immutable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "CAR_AUD")
public class CarAudit extends CarData implements AuditTable<Long> {
    @EmbeddedId
    private AuditId auditId;


    @Override
    public AuditId getCompositeKey() {
        return this.auditId;
    }

    @Override
    public Long getId() {
        return this.auditId.getId();
    }

    @Override
    public Integer getRev() {
        return this.auditId.getRev();
    }

}
