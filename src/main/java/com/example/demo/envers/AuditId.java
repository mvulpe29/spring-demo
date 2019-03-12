package com.example.demo.envers;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuditId implements Serializable {
    private long id;
    private Integer rev;

    public AuditId() {
    }

    public AuditId(long id, Integer rev) {
        this.id = id;
        this.rev = rev;
    }

    public AuditId(String id) {
        String[] parts = id.split("_");
        this.id = Long.parseLong(parts[0]);
        this.rev = Integer.parseInt(parts[1]);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof AuditId)) return false;
        AuditId that = (AuditId) obj;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRev(), that.getRev());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRev());
    }

    @Override
    public String toString() {
        return String.format("%s_%s", getId(), getRev());
    }

}
