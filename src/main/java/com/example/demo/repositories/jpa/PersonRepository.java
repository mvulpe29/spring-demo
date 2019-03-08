package com.example.demo.repositories.jpa;

import com.example.demo.addresses.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "people", path = "ppl")
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person>, QueryByExampleExecutor<Person>, JpaSpecificationExecutorWithProjection<Person> {

    List<Person> findByLastName(@Param("name") String name);

    List<Person> findByFirstName(@Param("firstName") String firstName);

    List<Person> findByFirstNameAndDeleted(@Param("firstName") String firstName, @Param("deleted") Boolean deleted);

    List<Person> findByFirstNameAndDeletedFalse(@Param("firstName") String firstName);

    //daca vreau mapat la projection trebuie sa pun in params si ce projection doresc

}
