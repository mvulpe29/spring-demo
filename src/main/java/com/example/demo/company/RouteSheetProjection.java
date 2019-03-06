package com.example.demo.company;

import com.example.demo.company.domain.Car;
import com.example.demo.company.domain.CarAudit;
import com.example.demo.company.domain.RouteSheet;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "routesheet-full", types = {RouteSheet.class})
public interface RouteSheetProjection {

    String getDate();

    String getLabel();

    Car getCar();

    CarAudit getCarAudit();
}
