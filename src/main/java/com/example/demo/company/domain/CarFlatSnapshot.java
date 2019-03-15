package com.example.demo.company.domain;

import javax.persistence.Embeddable;

@Embeddable
public class CarFlatSnapshot extends CarData {

    public CarFlatSnapshot() {
    }

    private String carCompanyName;
    private String carCompanyRegistryNo;
    private String carCompanyOwner;

    public String getCarCompanyName() {
        return carCompanyName;
    }

    public void setCarCompanyName(String carCompanyName) {
        this.carCompanyName = carCompanyName;
    }

    public String getCarCompanyRegistryNo() {
        return carCompanyRegistryNo;
    }

    public void setCarCompanyRegistryNo(String carCompanyRegistryNo) {
        this.carCompanyRegistryNo = carCompanyRegistryNo;
    }

    public String getCarCompanyOwner() {
        return carCompanyOwner;
    }

    public void setCarCompanyOwner(String carCompanyOwner) {
        this.carCompanyOwner = carCompanyOwner;
    }
}