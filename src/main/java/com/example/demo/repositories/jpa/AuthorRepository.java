package com.example.demo.repositories.jpa;

import com.example.demo.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "authors", rel = "authors")
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
