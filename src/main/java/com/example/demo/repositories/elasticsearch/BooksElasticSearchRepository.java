package com.example.demo.repositories.elasticsearch;

import com.example.demo.library.BookDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "books-documents", path = "books-documents")
public interface BooksElasticSearchRepository extends ElasticsearchRepository<BookDocument, Long> {

}

