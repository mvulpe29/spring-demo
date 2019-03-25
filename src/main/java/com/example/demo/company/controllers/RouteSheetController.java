package com.example.demo.company.controllers;

import com.example.demo.addresses.Address;
import com.example.demo.common.DynamicExpresionBuilder;
import com.example.demo.company.domain.RouteSheet;
import com.example.demo.repositories.jpa.RouteSheetRepository;
import com.querydsl.core.types.Predicate;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@SuppressWarnings("unchecked")
@RepositoryRestController
public class RouteSheetController {

    private final PagedResourcesAssembler<RouteSheet> pagedAssembler;
    private final RouteSheetRepository routeSheetRepository;

    @Autowired
    public RouteSheetController(PagedResourcesAssembler<RouteSheet> pagedAssembler, RouteSheetRepository routeSheetRepository) {
        this.pagedAssembler = pagedAssembler;
        this.routeSheetRepository = routeSheetRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/route-sheets/search/findAll", produces = "application/hal+json")
    public ResponseEntity<Page<RouteSheet>> findAll(
            @Or({
                    @Spec(path = "car.plate", params = "filter[plate][LIKE]", spec = LikeIgnoreCase.class),
                    @Spec(path = "label", params = "filter[label][LIKE]", spec = LikeIgnoreCase.class)
            }) Specification<RouteSheet> specification,
            Pageable pageable,
            PersistentEntityResourceAssembler assembler) {

        Page<RouteSheet> page = this.routeSheetRepository.findAll(specification, pageable);
        PagedResources<RouteSheet> pagedResources = pagedAssembler.toResource(page, (ResourceAssembler) assembler);
        return new ResponseEntity(pagedResources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/route-sheets/search/qdsl", produces = "application/hal+json")
    public ResponseEntity<Page<Address>> findAllThatMatch(
            @RequestParam Map<String, Object> filter,
            Pageable pageable,
            PersistentEntityResourceAssembler assembler) {

        Predicate predicate = DynamicExpresionBuilder.build(RouteSheet.class, "routeSheet", filter);

        Page<RouteSheet> page = this.routeSheetRepository.findAll(predicate, pageable);
        PagedResources<RouteSheet> pagedResources = pagedAssembler.toResource(page, (ResourceAssembler) assembler);
        return new ResponseEntity(pagedResources, HttpStatus.OK);
    }
}
