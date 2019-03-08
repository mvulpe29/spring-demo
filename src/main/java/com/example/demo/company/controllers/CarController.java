package com.example.demo.company.controllers;

import com.example.demo.company.domain.Car;
import com.example.demo.repositories.enversRevision.CarRevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionSort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@SuppressWarnings("unchecked")

@RepositoryRestController
public class CarController extends BaseCarController {

    private final CarRevisionRepository carRevisionRepository;

    @Autowired
    public CarController(CarRevisionRepository carRevisionRepository) {
        this.carRevisionRepository = carRevisionRepository;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}/revisions/{rev}", produces = "application/hal+json")
    public ResponseEntity<?> getCarRevision(@PathVariable("id") Long id, @PathVariable("rev") Integer rev) {
        return new ResponseEntity(this.carRevisionRepository.findRevision(id, rev), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/revisions/{rev}/entity", produces = "application/hal+json")
    public ResponseEntity<Car> getEntityFromCarRevision(@PathVariable("id") Long id, @PathVariable("rev") Integer rev) {
        Optional<Revision<Integer, Car>> carOptional = this.carRevisionRepository.findRevision(id, rev);
        Car car = carOptional.map(Revision::getEntity).orElse(null);
        return new ResponseEntity(car, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/latest-revision/entity", produces = "application/hal+json")
    public ResponseEntity<Car> getEntityFromLatestCarRevision(@PathVariable("id") Long id) {
        Page<Revision<Integer, Car>> page = this.carRevisionRepository.findRevisions(id, PageRequest.of(0, 2, RevisionSort.desc()));
        Car car = page.stream()
                .filter((Revision<Integer, Car> revision) -> revision.getEntity().getPlate() != null)
                .findFirst()
                .map(Revision::getEntity)
                .orElseThrow(null);


        return new ResponseEntity(car, HttpStatus.OK);
    }
}
