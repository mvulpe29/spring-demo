package com.example.demo.repositories.jpa;

import com.example.demo.company.domain.RouteSheetExcerpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "route-sheet-excerpts", path = "route-sheet-excerpts")
public interface RouteSheetExcerptRepository extends JpaRepository<RouteSheetExcerpt, Long>, JpaSpecificationExecutor {
}


