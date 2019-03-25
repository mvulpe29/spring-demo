package com.example.demo.company.domain.projections;

import com.example.demo.company.domain.Car;
import com.example.demo.company.domain.Company;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = {Car.class})
public interface CarProjection {

    Company getCompany();

    String getPlate();

    String getType();


}
