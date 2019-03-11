package com.example.demo.repositories.jpa;

import com.example.demo.company.domain.DriverAudit;
import com.example.demo.envers.AuditId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "drivers-audit", path = "drivers-audit")
public interface DriverAuditRepository extends JpaRepository<DriverAudit, AuditId> {
        Optional<DriverAudit> findFirstByIdOrderByRevDesc(Long id);
}
