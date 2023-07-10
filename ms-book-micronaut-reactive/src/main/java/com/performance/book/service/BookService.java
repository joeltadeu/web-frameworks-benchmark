package com.performance.book.service;

import com.performance.book.model.Book;
import com.performance.book.repository.BookRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
public class BookService {
    private final BookRepository repository;

    @Inject
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Mono<Book> findById(Long id) {
        return repository.findById(id);
    }

    public Flux<Book> findAll() {
        return repository.findAll();
    }
}
