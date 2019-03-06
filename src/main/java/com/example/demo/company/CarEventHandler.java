package com.example.demo.company;

import com.example.demo.repositories.jpa.CarAuditRepository;
import com.example.demo.repositories.jpa.RouteSheetRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
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
    }

    @HandleBeforeSave
    public void handleBeforeSave(Car car) {
    }

    @HandleBeforeDelete
    public void handleBeforeDelete(Car car) {
        this.routeSheetRepository.removeCarRelation(car.getId());
    }


}