package com.example.demo.company;

import com.example.demo.repositories.jpa.CarAuditRepository;
import com.example.demo.repositories.jpa.RouteSheetRepository;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class CarEventHandler {
    private final CarAuditRepository carAuditRepository;
    private final RouteSheetRepository routeSheetRepository;

    public CarEventHandler(CarAuditRepository carAuditRepository, RouteSheetRepository routeSheetRepository) {
        this.carAuditRepository = carAuditRepository;
        this.routeSheetRepository = routeSheetRepository;
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(Car car) {
        System.out.print(car);
    }

    @HandleBeforeSave
    public void handleBeforeSave(Car car) {
        System.out.print(car);
    }

    @HandleBeforeDelete
    public void handleBeforeDelete(Car car) {
        this.routeSheetRepository.removeCarRelation(car.getId());
    }

    @HandleBeforeLinkSave
    public void handleBeforeLinkSave(Car car, RouteSheet rs) {
        System.out.print(car);
    }

    @HandleAfterLinkSave
    public void handleAfterLinkSave(Car car, RouteSheet rs) {
        System.out.print(car);
    }

    @HandleBeforeLinkDelete
    public void handleBeforeLinkDelete(Car car, RouteSheet rs) {
        System.out.print(car);
    }

    @HandleAfterLinkDelete
    public void handleAfterLinkDelete(Car car, RouteSheet rs) {
        System.out.print(car);
    }

}