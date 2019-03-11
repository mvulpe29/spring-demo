package com.example.demo.envers;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.Instant;

@Embeddable
public class LastModifiedAuditId implements Serializable {

    private long id;
    private Instant lastModifiedAt;

    public LastModifiedAuditId() {
    }

    public LastModifiedAuditId(long id, Instant lastModifiedAt) {
        this.id = id;
        this.lastModifiedAt = lastModifiedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
