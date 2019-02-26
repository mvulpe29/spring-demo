package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "personprojection", types = { Person.class })
public interface PersonProjection {

    String getFirstName();

    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();
}
