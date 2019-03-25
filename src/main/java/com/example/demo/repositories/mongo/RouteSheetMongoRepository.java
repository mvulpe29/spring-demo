package com.example.demo.repositories.mongo;

import com.example.demo.company.domain.RouteSheetDocument;
import com.example.demo.company.domain.projections.RouteSheetDocumentProjection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "route-sheet-documents",
        path = "route-sheet-documents", excerptProjection = RouteSheetDocumentProjection.class)
public interface RouteSheetMongoRepository extends MongoRepository<RouteSheetDocument, Long> {
}
