package com.example.demo.company.domain.projections;

import com.example.demo.company.domain.RouteSheet;
import org.springframework.data.rest.core.config.Projection;

import java.time.Instant;

@Projection(name = "excerpt", types = {RouteSheet.class})
public interface RouteSheetProjection {

    Instant getDate();

    String getLabel();

    CarProjection getCar();

    DriverProjection getDriver();


}
