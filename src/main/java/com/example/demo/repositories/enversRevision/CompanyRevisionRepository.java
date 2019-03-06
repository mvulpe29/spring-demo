package com.example.demo.repositories.enversRevision;

import com.example.demo.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;


public interface CompanyRevisionRepository extends RevisionRepository<Company, Long, Integer>, JpaRepository<Company, Long> {

}


