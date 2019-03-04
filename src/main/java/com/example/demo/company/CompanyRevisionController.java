package com.example.demo.company;

import com.example.demo.repositories.enversRevision.CompanyRevisionRepository;
import com.example.demo.repositories.jpa.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@SuppressWarnings("unchecked")
@RepositoryRestController
public class CompanyRevisionController {

    private final PagedResourcesAssembler<Company> pagedAssembler;
    private final CompanyRepository companyRepository;
    private final CompanyRevisionRepository companyRevisionRepository;

    @Autowired
    public CompanyRevisionController(PagedResourcesAssembler<Company> pagedAssembler, CompanyRepository companyRepository, CompanyRevisionRepository companyRevisionRepository) {
        this.pagedAssembler = pagedAssembler;
        this.companyRepository = companyRepository;
        this.companyRevisionRepository = companyRevisionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/companies/{id}/revisions", produces = "application/hal+json")
    public ResponseEntity<?> getCompaniesRevisions(@PathVariable("id") Long id, Pageable pageable) {

        return new ResponseEntity(this.companyRevisionRepository.findRevisions(id, pageable), HttpStatus.OK);
    }
}
