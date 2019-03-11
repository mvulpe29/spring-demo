package com.example.demo.addresses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Audited
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    private long id;

    private String firstName;
    private String lastName;
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Embedded
    private Contact contact;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "phone", column = @Column(name = "work_phone")),
                    @AttributeOverride(name = "email", column = @Column(name = "work_email"))
            }
    )
    private Contact workContact;


    @ElementCollection
    @CollectionTable(name = "life_events", joinColumns = @JoinColumn(name = "person_id"))
    @OrderColumn
    private List<LifeEvent> lifeEvents;

    public Person() {
        this.deleted = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getWorkContact() {
        return workContact;
    }

    public void setWorkContact(Contact workContact) {
        this.workContact = workContact;
    }

    public List<LifeEvent> getLifeEvents() {
        return lifeEvents;
    }

    public void setLifeEvents(List<LifeEvent> lifeEvents) {
        this.lifeEvents = lifeEvents;
    }
}