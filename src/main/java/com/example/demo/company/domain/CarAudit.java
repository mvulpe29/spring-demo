package com.example.demo.company.domain;

import com.example.demo.common.AuditTable;
import com.example.demo.envers.AuditId;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Immutable
@Subselect("Select * from CAR_AUD")
public class CarAudit extends CarData implements AuditTable<Long> {
    @EmbeddedId
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "id", column = @Column(name = "id")),
                    @AttributeOverride(name = "rev", column = @Column(name = "rev"))
            }
    )
    private AuditId auditId;

//    @Override
//    @Column(name = "created_by")
//    public String getCreatedBy() {
//        return super.getCreatedBy();
//    }
//
//    @Override
//    @Column(name = "created_at")
//    public Instant getCreatedAt() {
//        return super.getCreatedAt();
//    }
//
//    @Override
//    @Column(name = "last_modified_by")
//    public String getLastModifiedBy() {
//        return super.getLastModifiedBy();
//    }
//
//    @Override
//    @Column(name = "last_modified_at")
//    public Instant getLastModifiedAt() {
//        return super.getLastModifiedAt();
//    }

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
