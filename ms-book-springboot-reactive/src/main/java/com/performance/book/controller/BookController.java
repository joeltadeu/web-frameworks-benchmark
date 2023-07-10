package com.performance.book.controller;

import com.performance.book.model.Book;
import com.performance.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Book> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
