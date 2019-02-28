package com.example.demo.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor {

    List<Company> findAllByNameContaining(@Param("name") String name);
}


