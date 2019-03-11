package com.example.demo.common;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Audited
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity implements Serializable {
    private long id;
    private long version;

    @CreatedBy
    @ReadOnlyProperty
    private String createdBy;

    @CreatedDate
    @ReadOnlyProperty
    private Instant createdAt;

    @LastModifiedBy
    @ReadOnlyProperty
    private String lastModifiedBy;

    @LastModifiedDate
    @ReadOnlyProperty
    private Instant lastModifiedAt;


    public BaseEntity() {
    }


    @Id
    @Column(name = "id", updatable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
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

