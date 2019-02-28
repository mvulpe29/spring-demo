package com.example.demo;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
    private String phone;
    private String email;

    public Contact() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}