package com.example.demo.repositories.jpa;

import com.example.demo.company.domain.AuditId;
import com.example.demo.company.domain.CarAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "cars-audit", path = "cars-audit")
public interface CarAuditRepository extends JpaRepository<CarAudit, AuditId>, JpaSpecificationExecutor {

    List<CarAudit> findAllByIdAndRev(@Param("id") long id, @Param("rev") long rev);

    CarAudit findFirstByIdOrderByRevDesc(@Param("id") long id);
}


