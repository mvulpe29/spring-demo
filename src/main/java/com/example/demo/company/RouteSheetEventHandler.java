package com.example.demo.company;

import com.example.demo.repositories.jpa.CarAuditRepository;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler
public class RouteSheetEventHandler {
    private final CarAuditRepository carAuditRepository;

    public RouteSheetEventHandler(CarAuditRepository carAuditRepository) {
        this.carAuditRepository = carAuditRepository;
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(RouteSheet routeSheet) {
        this.addCarAuditToRouteSheet(routeSheet);
    }

    @HandleBeforeSave
    public void handleBeforeSave(RouteSheet routeSheet) {
    }

    @HandleBeforeLinkSave
    public void handleBeforeLinkSave(RouteSheet routeSheet, Car oldCar) {
        this.addCarAuditToRouteSheet(routeSheet);
    }

    @HandleAfterLinkSave
    public void handleAfterLinkSave(RouteSheet routeSheet, Car oldCar) {

    }

    @HandleBeforeLinkDelete
    public void handleBeforeLinkDelete(RouteSheet routeSheet, Car oldCar) {
    }

    @HandleAfterLinkDelete
    public void handleAfterLinkDelete(RouteSheet routeSheet, Car oldCar) {
    }


    private void addCarAuditToRouteSheet(RouteSheet routeSheet) {
        Optional.of(routeSheet).map(RouteSheet::getCar).ifPresent(car -> {
            CarAudit carAudit = this.carAuditRepository.findFirstByIdOrderByRevDesc(car.getId());
            routeSheet.setCarAudit(carAudit);
        });
    }
}