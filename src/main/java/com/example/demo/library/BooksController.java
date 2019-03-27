package com.example.demo.library;

import com.example.demo.repositories.elasticsearch.BooksElasticSearchRepository;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestController
public class BooksController {

    private final BooksElasticSearchRepository booksElasticSearchRepository;

    public BooksController(BooksElasticSearchRepository booksElasticSearchRepository) {
        this.booksElasticSearchRepository = booksElasticSearchRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/books/search/findAll", produces = "application/hal+json")
    public ResponseEntity<?> getBooks(@RequestParam("filter") String filter, Pageable pageable) {
        Page<BookDocument> page = this.booksElasticSearchRepository.search(
                new SimpleQueryStringBuilder(filter), pageable);
        return ResponseEntity.ok(page);

    }
}
