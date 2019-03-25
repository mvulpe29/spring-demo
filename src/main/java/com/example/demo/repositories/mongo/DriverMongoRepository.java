package com.example.demo.repositories.mongo;

import com.example.demo.company.domain.DriverDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "driver-documents", path = "driver-documents")
public interface DriverMongoRepository extends MongoRepository<DriverDocument, Long> {
}
