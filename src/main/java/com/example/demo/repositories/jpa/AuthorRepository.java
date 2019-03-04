package com.example.demo.repositories.jpa;

import com.example.demo.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
