package com.performance.book.service;

import com.performance.book.model.Book;
import com.performance.book.repository.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BookService {

    private final BookRepository repository;

    @Inject
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book findById(Long id) {
        return repository.findById(id);
    }

    public List<Book> findAll() {
        return repository.listAll();
    }
}
