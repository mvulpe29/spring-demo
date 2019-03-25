package com.example.demo.company.domain.projections;

import com.example.demo.company.domain.RouteSheetDocument;
import org.springframework.data.rest.core.config.Projection;

import java.time.Instant;

@Projection(name = "excerpt", types = {RouteSheetDocument.class})
public interface RouteSheetDocumentProjection {

    Instant getDate();

    String getLabel();

    CarProjection getCar();

    DriverProjection getDriver();

}
