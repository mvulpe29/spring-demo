package com.example.demo.addresses;

import com.example.demo.repositories.enversRevision.PersonRevisionRepository;
import com.example.demo.repositories.jpa.PersonRepository;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RepositoryRestController
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonRevisionRepository personRevisionRepository;


    @Autowired
    public PersonController(PersonRepository personRepository, PersonRevisionRepository personRevisionRepository) {
        this.personRepository = personRepository;
        this.personRevisionRepository = personRevisionRepository;
    }

    @RequestMapping(method = GET, value = "/people/search/findAll")
    public @ResponseBody
    ResponseEntity<?> getCustomerListView(@And({
            @Spec(path = "firstName", params = "filter[firstName][EQ]", spec = Equal.class),
            @Spec(path = "lastName", params = "filter[lastName][EQ]", spec = Equal.class)
    }) Specification<Person> specification) throws URISyntaxException {

        Page<PersonProjection> personList = personRepository.findAll(specification, PersonProjection.class, Pageable.unpaged());
        return ResponseEntity.ok(personList);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/people/{id}/revisions", produces = "application/hal+json")
    public ResponseEntity<?> getCompaniesRevisions(@PathVariable("id") Long id, Pageable pageable) {
        return new ResponseEntity(this.personRevisionRepository.findRevisions(id), HttpStatus.OK);
    }
}
