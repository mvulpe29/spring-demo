package com.example.demo.company.services;

import com.example.demo.company.domain.Car;
import com.example.demo.repositories.jpa.RouteSheetRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class CarEventHandler {
    private final RouteSheetRepository routeSheetRepository;

    public CarEventHandler(RouteSheetRepository routeSheetRepository) {
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
//        this.routeSheetRepository.removeCarRelation(car.getId());
    }


}