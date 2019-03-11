package com.example.demo.repositories.enversRevision;

import com.example.demo.company.domain.Driver;
import org.springframework.data.repository.history.RevisionRepository;


public interface DriverRevisionRepository extends RevisionRepository<Driver, Long, Integer> {

}


