package com.performance.book.controller;

import com.performance.book.model.Book;
import com.performance.book.service.BookService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/v1/books")
public class BookController {

    private final BookService service;

    @Inject
    public BookController(BookService service) {
        this.service = service;
    }

    @Get("/{id}")
    public Mono<Book> findById(Long id) {
        return service.findById(id);
    }

    @Get
    public Flux<Book> findAll() {
        return service.findAll();
    }

}
