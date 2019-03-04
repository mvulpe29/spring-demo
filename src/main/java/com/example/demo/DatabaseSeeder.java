package com.example.demo;

import com.example.demo.addresses.Address;
import com.example.demo.addresses.Person;
import com.example.demo.company.Company;
import com.example.demo.library.Author;
import com.example.demo.library.Book;
import com.example.demo.repositories.jpa.AddressRepository;
import com.example.demo.repositories.jpa.AuthorRepository;
import com.example.demo.repositories.jpa.BookRepository;
import com.example.demo.repositories.jpa.CompanyRepository;
import com.example.demo.repositories.jpaSpecificationExecutorWithProjection.PersonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseSeeder {

    final private PersonRepository personRepository;
    final private AddressRepository addressRepository;
    final private CompanyRepository companyRepository;
    final private BookRepository bookRepository;
    final private AuthorRepository authorRepository;

    public DatabaseSeeder(PersonRepository personRepository,
                          AddressRepository addressRepository,
                          CompanyRepository companyRepository,
                          BookRepository bookRepository,
                          AuthorRepository authorRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedAddresses();
        seedPeople();
        seedCompanies();
        seedBooks();
        seedAuthors();
    }

    private void seedAddresses() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Address>> typeReference = new TypeReference<List<Address>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/seed/addresses.json");
        try {
            List<Address> addresses = mapper.readValue(inputStream, typeReference);
            addressRepository.saveAll(addresses);
            System.out.println("addresses saved!");
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
            System.out.println("people saved!");
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
            System.out.println("companies saved!");
        } catch (IOException e) {
            System.out.println("Unable to save companies: " + e.getMessage());
        }
    }

    private void seedBooks() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/seed/books.json");
        try {
            List<Book> books = mapper.readValue(inputStream, typeReference);
            bookRepository.saveAll(books);
            System.out.println("books saved!");
        } catch (IOException e) {
            System.out.println("Unable to save books: " + e.getMessage());
        }
    }

    private void seedAuthors() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Author>> typeReference = new TypeReference<List<Author>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/seed/authors.json");
        try {
            List<Author> authors = mapper.readValue(inputStream, typeReference);
            authorRepository.saveAll(authors);
            System.out.println("authors saved!");
        } catch (IOException e) {
            System.out.println("Unable to save books: " + e.getMessage());
        }
    }
}
