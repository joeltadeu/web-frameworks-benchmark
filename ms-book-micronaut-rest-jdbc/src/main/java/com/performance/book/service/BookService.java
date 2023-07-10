package com.performance.book.service;

import com.performance.book.model.Book;
import com.performance.book.repository.BookRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public List<Book> findAll() {
       var iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
