package com.example.demo.company.services;

import com.example.demo.company.domain.Driver;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class DriverEventHandler {

    public DriverEventHandler() {
    }


    @HandleBeforeCreate
    public void handleBeforeCreate(Driver driver) {
    }

    @HandleBeforeSave
    public void handleBeforeSave(Driver driver) {
    }

    @HandleBeforeDelete
    public void handleBeforeDelete(Driver driver) {

    }


}