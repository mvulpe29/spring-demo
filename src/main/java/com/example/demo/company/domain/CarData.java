package com.example.demo.company.domain;

import com.example.demo.common.Auditable;
import org.hibernate.envers.Audited;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Embeddable
@Audited
public class CarData extends Auditable {
    private String plate;
    private String type;

    public CarData() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
