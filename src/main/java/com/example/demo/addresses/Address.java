package com.example.demo.addresses;

import com.example.demo.BaseEntityInterface;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Entity
@Audited
public class Address implements BaseEntityInterface {

    @Id
    private long id;

    private String streetName;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Person> people;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @PrePersist
    @PreUpdate
    public void updatePeopleAssociation() {
        Optional.ofNullable(this.people)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .forEach(person -> person.setAddress(this));
    }
}