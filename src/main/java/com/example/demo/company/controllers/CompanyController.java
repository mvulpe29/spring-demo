package com.example.demo.company.controllers;

import com.example.demo.addresses.Address;
import com.example.demo.company.domain.Company;
import com.example.demo.repositories.jpa.CompanyRepository;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("unchecked")
@RepositoryRestController
public class CompanyController {

    private final PagedResourcesAssembler<Company> pagedAssembler;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(PagedResourcesAssembler<Company> pagedAssembler, CompanyRepository companyRepository) {
        this.pagedAssembler = pagedAssembler;
        this.companyRepository = companyRepository;
    }

    @PreAuthorize("hasPermission(#owner, 'read')")
    @RequestMapping(method = RequestMethod.GET, path = "/companies/search/findAll", produces = "application/hal+json")
    public ResponseEntity<Page<Address>> getCompanies(
            @Spec(path = "owner", params = "filter[owner][EQ]", spec = Equal.class) Specification<Company> specification,
            @RequestParam("filter[owner][EQ]") String owner,
            Pageable pageable,
            PersistentEntityResourceAssembler assembler) {

        Page<Company> page = this.companyRepository.findAll(specification, pageable);
        PagedResources<Company> pagedResources = pagedAssembler.toResource(page, (ResourceAssembler) assembler);
        return new ResponseEntity(pagedResources, HttpStatus.OK);
    }
}
