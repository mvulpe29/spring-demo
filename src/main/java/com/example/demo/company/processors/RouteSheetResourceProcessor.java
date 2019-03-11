package com.example.demo.company.processors;

import com.example.demo.company.controllers.CarController;
import com.example.demo.company.controllers.DriverController;
import com.example.demo.company.domain.RouteSheet;
import com.example.demo.utils.BasePathAwareLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
public class RouteSheetResourceProcessor implements ResourceProcessor<Resource<RouteSheet>> {

    @Autowired
    BasePathAwareLinks basePathAwareLinks;

    @Override
    public Resource<RouteSheet> process(Resource<RouteSheet> resource) {

        Optional.ofNullable(resource.getContent().getCarAuditId()).ifPresent(carAuditId -> {
            resource.add(
                    basePathAwareLinks.underBasePath(
                            linkTo(
                                    methodOn(CarController.class)
                                            .getCarRevision(carAuditId.getId(), carAuditId.getRev())
                            )
                    ).withRel("carRevision"));

            resource.add(
                    basePathAwareLinks.underBasePath(
                            linkTo(
                                    methodOn(CarController.class)
                                            .getEntityFromCarRevision(carAuditId.getId(), carAuditId.getRev())
                            )
                    ).withRel("carRevisionEntity"));

            resource.add(
                    basePathAwareLinks.underBasePath(
                            linkTo(
                                    methodOn(CarController.class)
                                            .getEntityFromLatestCarRevision(carAuditId.getId())
                            )
                    ).withRel("carLatestRevisionEntity"));
        });

        Optional.ofNullable(resource.getContent().getDriverLastModifiedAuditId())
                .ifPresent(lastModifiedAuditId -> {
                    resource.add(
                            basePathAwareLinks.underBasePath(
                                    linkTo(
                                            methodOn(DriverController.class)
                                                    .getDriverFromLastModifiedAuditId(
                                                            lastModifiedAuditId.getId(),
                                                            lastModifiedAuditId.getLastModifiedAt()
                                                    )
                                    )
                            ).withRel("driverRevisionEntity"));

                });


        return resource;
    }
}
