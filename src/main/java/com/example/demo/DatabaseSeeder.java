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
        seedCompanies();
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
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }
}
