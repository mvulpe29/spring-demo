package com.example.demo.company.domain.projections;

import com.example.demo.company.domain.Driver;
import com.example.demo.company.domain.Employee;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = {Driver.class})
public interface DriverProjection {

    Employee getEmployee();

    String getLicenseCategory();


}
