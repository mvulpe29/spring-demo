package com.example.demo.company;

import com.example.demo.repositories.enversRevision.CompanyRevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SuppressWarnings("unchecked")
@RepositoryRestController
public class CompanyRevisionController {
    private final CompanyRevisionRepository companyRevisionRepository;

    @Autowired
    public CompanyRevisionController(CompanyRevisionRepository companyRevisionRepository) {
        this.companyRevisionRepository = companyRevisionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/companies/{id}/revisions", produces = "application/hal+json")
    public ResponseEntity<?> getCompaniesRevisions(@PathVariable("id") Long id, Pageable pageable) {

        return new ResponseEntity(this.companyRevisionRepository.findRevisions(id, pageable), HttpStatus.OK);
    }
}
