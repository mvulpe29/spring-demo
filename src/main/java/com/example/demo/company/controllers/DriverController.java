package com.example.demo.company.controllers;

import com.example.demo.company.domain.Driver;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.time.Instant;

@SuppressWarnings("unchecked")
@RepositoryRestController
public class DriverController {
    private final EntityManager entityManager;

    @Autowired
    public DriverController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/drivers/{id}/revisions", produces = "application/hal+json")
    public ResponseEntity<?> getDriverFromLastModifiedAuditId(@PathVariable("id") Long id,
                                                              @RequestParam("lastModifiedAt") Instant lastModifiedAt) {

        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        Driver driver = (Driver) auditReader
                .createQuery()
                .forRevisionsOfEntity(Driver.class, true, true)
                .add(AuditEntity.id().eq(id))
                .add(AuditEntity.property("lastModifiedAt")
                        .eq(lastModifiedAt))
                .getSingleResult();

        return new ResponseEntity(driver, HttpStatus.OK);
    }
}
