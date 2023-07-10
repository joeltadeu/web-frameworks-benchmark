package com.performance.book.service;

import com.performance.book.model.Book;
import com.performance.book.repository.BookRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BookService {
    private final BookRepository repository;

    @Inject
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Multi<Book> findAll() {
        return repository.findAll();
    }

    public Uni<Book> findById(Long id) {
        return repository.findById(id);
    }
}
