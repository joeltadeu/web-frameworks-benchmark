package com.performance.book.service;

import com.performance.book.repository.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.performance.book.model.Book;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookService {
    private final BookRepository repository;

    @Inject
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }
}
