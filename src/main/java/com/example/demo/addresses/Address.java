package com.example.demo.addresses;

import com.example.demo.BaseEntityInterface;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Audited
public class Address implements BaseEntityInterface {

    @Id
    private long id;

    private String streetName;

    @OneToMany(mappedBy = "address", orphanRemoval = true)
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
}