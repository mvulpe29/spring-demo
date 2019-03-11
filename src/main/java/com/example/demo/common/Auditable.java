package com.example.demo.common;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Audited
@EntityListeners({AuditingEntityListener.class})
public class Auditable implements Serializable {

    @CreatedBy
    @ReadOnlyProperty
    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @ReadOnlyProperty
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedBy
    @ReadOnlyProperty
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @LastModifiedDate
    @ReadOnlyProperty
    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;


    public Auditable() {
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}

