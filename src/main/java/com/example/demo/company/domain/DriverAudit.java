package com.example.demo.company.domain;

import com.example.demo.common.AuditTable;
import com.example.demo.envers.AuditId;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Subselect("select * from DRIVER_AUD")
@IdClass(AuditId.class)
public class DriverAudit extends DriverData implements AuditTable<Long> {

    @Id
    @Column(name = "rev")
    private Integer rev;

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    @Override
    public AuditId getCompositeKey() {
        return new AuditId(this.getId(), this.getRev());
    }
}
