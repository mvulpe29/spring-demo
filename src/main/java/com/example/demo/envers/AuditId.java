package com.example.demo.envers;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class AuditId implements Serializable {
    @Column(name = "id")
    private long id;
    @Column(name = "rev")
    private Integer rev;

    public AuditId() {
    }

    public AuditId(long id, Integer rev) {
        this.id = id;
        this.rev = rev;
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

}
