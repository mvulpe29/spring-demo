package com.example.demo.company.services;

import com.example.demo.company.domain.RouteSheet;
import com.example.demo.company.domain.mappers.RouteSheetMapper;
import com.example.demo.envers.AuditId;
import com.example.demo.repositories.enversRevision.CarRevisionRepository;
import com.example.demo.repositories.jpa.DriverAuditRepository;
import com.example.demo.repositories.mongo.RouteSheetMongoRepository;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RepositoryEventHandler
public class RouteSheetEventHandler {
    private final CarRevisionRepository carRevisionRepository;
    private final DriverAuditRepository driverAuditRepository;
    private final RouteSheetMongoRepository routeSheetMongoRepository;

    public RouteSheetEventHandler(CarRevisionRepository carRevisionRepository,
                                  DriverAuditRepository driverAuditRepository, RouteSheetMongoRepository routeSheetMongoRepository) {
        this.carRevisionRepository = carRevisionRepository;
        this.driverAuditRepository = driverAuditRepository;
        this.routeSheetMongoRepository = routeSheetMongoRepository;
    }


    @HandleBeforeCreate
    public void handleBeforeCreate(RouteSheet routeSheet) {
        this.addCarAuditToRouteSheet(routeSheet);
        this.addDriverAuditToRouteSheet(routeSheet);
    }


    @HandleBeforeSave
    public void handleBeforeSave(RouteSheet routeSheet) {
    }

    @HandleAfterSave
    public void handleAfterSave(RouteSheet routeSheet) {
        this.routeSheetMongoRepository.save(RouteSheetMapper.INSTANCE.toRouteSheetDocument(routeSheet));
    }

    @HandleBeforeLinkSave
    public void handleBeforeLinkSave(RouteSheet routeSheet, Object linked) {
        this.addCarAuditToRouteSheet(routeSheet);
        this.addDriverAuditToRouteSheet(routeSheet);
    }

    @HandleAfterLinkSave
    public void handleAfterLinkSave(RouteSheet routeSheet, Object linked) {
        this.routeSheetMongoRepository.save(RouteSheetMapper.INSTANCE.toRouteSheetDocument(routeSheet));
    }

    @HandleBeforeLinkDelete
    public void handleBeforeLinkDelete(RouteSheet routeSheet, Object linked) {
    }

    @HandleAfterLinkDelete
    public void handleAfterLinkDelete(RouteSheet routeSheet, Object linked) {
    }


    private void addCarAuditToRouteSheet(RouteSheet routeSheet) {
        Optional.of(routeSheet).map(RouteSheet::getCar).ifPresent(car -> {
            carRevisionRepository.findLastChangeRevision(car.getId()).ifPresent(revision -> {
                AuditId carAuditId = new AuditId(car.getId(), revision.getRequiredRevisionNumber());
                routeSheet.setCarAuditId(carAuditId);
            });
        });
    }

    private void addDriverAuditToRouteSheet(RouteSheet routeSheet) {
        Optional.of(routeSheet).map(RouteSheet::getDriver).ifPresent(driver -> {
            driverAuditRepository.findFirstByIdOrderByRevDesc(driver.getId())
                    .ifPresent(routeSheet::setDriverAudit);
        });
    }
}