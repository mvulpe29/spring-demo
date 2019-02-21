package com.example.demo;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

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

        List<Person> personList = personRepository.findAll(specification);
        return ResponseEntity.ok(personList);
    }
}
