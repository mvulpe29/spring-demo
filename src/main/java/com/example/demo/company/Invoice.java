package com.example.demo.company;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Audited
public class Invoice {

    @Id
    private long id;

    private String code;
    private Instant date;

    public Invoice() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
