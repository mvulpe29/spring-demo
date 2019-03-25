package com.example.demo.company.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RouteSheetDocument extends RouteSheet {
    @Override
    public Car getCar() {
        return super.getCar();
    }
}
