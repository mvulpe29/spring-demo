package com.example.demo;

import com.example.demo.addresses.Address;
import com.example.demo.addresses.Person;
import com.example.demo.company.domain.*;
import com.example.demo.library.Author;
import com.example.demo.library.Book;
import com.example.demo.repositories.jpa.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseSeeder {

    final private PersonRepository personRepository;
    final private AddressRepository addressRepository;
    final private CompanyRepository companyRepository;
    final private BookRepository bookRepository;
    final private AuthorRepository authorRepository;
    final private InvoiceRepository invoiceRepository;
    final private RouteSheetRepository routeSheetRepository;
    final private CarRepository carRepository;
    final private DriverRepository driverRepository;

    public DatabaseSeeder(PersonRepository personRepository,
                          AddressRepository addressRepository,
                          CompanyRepository companyRepository,
                          BookRepository bookRepository,
                          AuthorRepository authorRepository,
                          InvoiceRepository invoiceRepository,
                          RouteSheetRepository routeSheetRepository,
                          CarRepository carRepository,
                          DriverRepository driverRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.invoiceRepository = invoiceRepository;
        this.routeSheetRepository = routeSheetRepository;
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedEntities("addresses", Address.class, addressRepository);
        seedEntities("people", Person.class, personRepository);
        seedEntities("books", Book.class, bookRepository);
        seedEntities("authors", Author.class, authorRepository);
        seedEntities("companies", Company.class, companyRepository);
        seedEntities("invoices", Invoice.class, invoiceRepository);
        seedEntities("route-sheets", RouteSheet.class, routeSheetRepository);
        seedEntities("cars", Car.class, carRepository);
        seedEntities("drivers", Driver.class, driverRepository);
    }

    private <T> void seedEntities(String fileName, Class<T> type, JpaRepository repository) {
        List<T> entities = Optional.ofNullable(readEntities(fileName, type)).orElseGet(Collections::emptyList);
        repository.saveAll(entities);
        System.out.println(fileName + " saved!");
    }

    private <T> List<T> readEntities(String fileName, Class<T> type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            InputStream inputStream = new ClassPathResource("/seed/" + fileName + ".json").getInputStream();
            return mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (IOException e) {
            System.out.println("Unable to read " + fileName + ": " + e.getMessage());
        }
        return null;
    }
}
