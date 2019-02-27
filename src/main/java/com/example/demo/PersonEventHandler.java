package com.example.demo;

import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class PersonEventHandler {

    @HandleBeforeCreate
    public void handlePersonSave(Person p) {
        System.out.print(p.getFirstName());
    }

    @HandleBeforeSave
    public void handlePersonBeforeSave(Person p) {
        System.out.print(p.getFirstName());
    }

    @HandleAfterSave
    public void handlePersonAfterSave(Person p) {
        System.out.print(p.getFirstName());
    }

}