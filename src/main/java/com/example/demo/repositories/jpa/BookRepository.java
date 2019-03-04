package com.example.demo.repositories.jpa;

import com.example.demo.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
