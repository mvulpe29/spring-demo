package com.example.demo.addresses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor {

}


