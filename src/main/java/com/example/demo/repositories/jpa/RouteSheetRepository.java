package com.example.demo.repositories.jpa;

import com.example.demo.company.domain.RouteSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "route-sheets", path = "route-sheets")
public interface RouteSheetRepository extends JpaRepository<RouteSheet, Long>, JpaSpecificationExecutor {
    List<RouteSheet> findAllByCarMutableId(long id);

    @Transactional
    List<RouteSheet> removeByCarMutableId(long id);

    @Modifying
    @Transactional
    @Query(value = "update RouteSheet r set r.carMutable = null where r.carMutable.id = :id")
    int removeCarRelation(@Param("id") long id);
}


