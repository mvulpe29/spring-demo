package com.example.demo.addresses;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/person-custom")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(method = GET, value = "")
    public @ResponseBody
    ResponseEntity<?> getCustomerListView(@And({
            @Spec(path = "firstName", params = "filter[firstName][EQ]", spec = Equal.class),
            @Spec(path = "lastName", params = "filter[lastName][EQ]", spec = Equal.class)
    }) Specification<Person> specification) throws URISyntaxException {

        Page<PersonProjection> personList = personRepository.findAll(specification, PersonProjection.class, Pageable.unpaged());
        return ResponseEntity.ok(personList);
    }
}
