package com.example.demo.repositories.jpa;

import com.example.demo.company.domain.CarAudit;
import com.example.demo.envers.AuditId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cars-audit", path = "cars-audit")
public interface CarAuditRepository extends JpaRepository<CarAudit, AuditId> {

}
