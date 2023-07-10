package com.performance.book.controller;

import com.performance.book.model.Book;
import com.performance.book.service.BookService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;
import java.util.Optional;

@Controller("/v1/books")
public class BookController {
    protected final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @Get("/{id}")
    public Optional<Book> findById(Long id) {
        return service.findById(id);
    }

    @Get
    public List<Book> findAll() {
        return service.findAll();
    }
}
