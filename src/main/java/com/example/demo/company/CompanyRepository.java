package com.example.demo.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;


@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor {

    @PreAuthorize("hasPermission(#owner, 'read')")
    @RestResource(path = "nameContains", rel = "nameContains")
    Page<Company> findAllByOwnerAndNameContaining(@Param("owner") String owner, @Param("name") String name, Pageable pageable);
}


