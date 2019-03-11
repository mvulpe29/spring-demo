package com.example.demo.repositories.jpa;

import com.example.demo.company.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "drivers", path = "drivers")
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
