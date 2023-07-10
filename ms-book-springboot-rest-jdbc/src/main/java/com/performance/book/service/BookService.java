package com.performance.book.service;

import com.performance.book.model.Book;
import com.performance.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookService {
    private final BookRepository repository;

    public Book findById(Long id) {
        var optionalBook = repository.findById(id);
        if (optionalBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");

        return optionalBook.get();
    }

    public List<Book> findAll() {
        log.info("Finding all books");
        return repository.findAll();
    }
}
