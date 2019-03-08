package com.example.demo.company.services;

import com.example.demo.envers.AuditId;
import com.example.demo.company.domain.Car;
import com.example.demo.company.domain.RouteSheet;
import com.example.demo.repositories.enversRevision.CarRevisionRepository;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RepositoryEventHandler
public class RouteSheetEventHandler {
    private final CarRevisionRepository carRevisionRepository;

    public RouteSheetEventHandler(CarRevisionRepository carRevisionRepository) {
        this.carRevisionRepository = carRevisionRepository;
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
        Optional.of(routeSheet).map(RouteSheet::getCarMutable).ifPresent(car -> {
            carRevisionRepository.findLastChangeRevision(car.getId()).ifPresent(revision -> {
                AuditId carAuditId = new AuditId(car.getId(), revision.getRequiredRevisionNumber());
                routeSheet.setCarAuditId(carAuditId);
            });
        });
    }
}