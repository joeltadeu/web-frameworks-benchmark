package com.performance.book.service;

import com.performance.book.model.Book;
import com.performance.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Mono<Book> findById(Long id) {
        return repository.findById(id);
    }

    public Flux<Book> findAll() {
        return repository.findAll();
    }
}
