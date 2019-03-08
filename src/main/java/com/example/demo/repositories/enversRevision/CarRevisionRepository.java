package com.example.demo.repositories.enversRevision;

import com.example.demo.company.domain.Car;
import org.springframework.data.repository.history.RevisionRepository;


public interface CarRevisionRepository extends RevisionRepository<Car, Long, Integer> {

}


