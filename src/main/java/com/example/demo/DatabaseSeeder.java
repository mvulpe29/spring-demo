package com.example.demo;

import com.example.demo.company.Company;
import com.example.demo.company.CompanyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class DatabaseSeeder {

    final private PersonRepository personRepository;
    final private AddressRepository addressRepository;
    final private CompanyRepository companyRepository;

    public DatabaseSeeder(PersonRepository personRepository, AddressRepository addressRepository, CompanyRepository companyRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedAddresses();
        seedPeople();
        seedCompanies();
    }

    private void seedAddresses() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Address>> typeReference = new TypeReference<List<Address>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/seed/addresses.json");
        try {
            List<Address> addresses = mapper.readValue(inputStream, typeReference);
            addressRepository.saveAll(addresses);
            System.out.println("Addresses Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save addresses: " + e.getMessage());
        }
    }

    private void seedPeople() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/seed/people.json");
        try {
            List<Person> people = mapper.readValue(inputStream, typeReference);
            people = people.stream().peek(person -> {
                long id = person.getAddress().getId();
                Address address = addressRepository.getOne(id);
                person.setAddress(address);
            }).collect(Collectors.toList());
            personRepository.saveAll(people);
            System.out.println("People Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save people: " + e.getMessage());
        }
    }

    private void seedCompanies() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Company>> typeReference = new TypeReference<List<Company>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/seed/companies.json");
        try {
            List<Company> companies = mapper.readValue(inputStream, typeReference);
            companyRepository.saveAll(companies);
            System.out.println("Companies Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save companies: " + e.getMessage());
        }
    }
}
