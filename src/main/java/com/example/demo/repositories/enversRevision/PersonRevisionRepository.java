package com.example.demo.repositories.enversRevision;

import com.example.demo.addresses.Person;
import org.springframework.data.repository.history.RevisionRepository;


public interface PersonRevisionRepository extends RevisionRepository<Person, Long, Integer> {

}


