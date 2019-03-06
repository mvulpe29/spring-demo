package com.example.demo.company.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuditId implements Serializable {
    private long id;
    private long rev;

    public AuditId() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRev() {
        return rev;
    }

    public void setRev(long rev) {
        this.rev = rev;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof CarAudit)) return false;
        AuditId that = (AuditId) obj;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRev(), that.getRev());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRev());
    }

}
