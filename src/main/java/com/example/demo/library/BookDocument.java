package com.example.demo.library;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Document(indexName = "library", type = "book")
public class BookDocument extends Book{
}