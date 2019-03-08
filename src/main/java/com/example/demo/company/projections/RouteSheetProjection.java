package com.example.demo.company.projections;

import com.example.demo.company.domain.RouteSheet;
import org.springframework.data.rest.core.config.Projection;

import java.time.Instant;

@Projection(name = "routesheet-full", types = {RouteSheet.class})
public interface RouteSheetProjection {

    Instant getDate();

    String getLabel();

}
