package com.example.demo.repositories.jpa;

import com.example.demo.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "books", rel = "books")
public interface BookRepository extends JpaRepository<Book, Long> {
}
