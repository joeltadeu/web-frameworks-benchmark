package com.performance.book.service;

import com.performance.book.model.Book;
import com.performance.book.repository.BookRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book createBook(Book book) throws SQLException {
        validateBook(book);
        return repository.save(book);
    }

    public List<Book> getAllBooks() throws SQLException {
        return repository.findAll();
    }

    public Optional<Book> getBookById(Long id) throws SQLException {
        return repository.findById(id);
    }

    public boolean updateBook(Long id, Book book) throws SQLException {
        validateBook(book);

        Optional<Book> existingBook = repository.findById(id);
        if (existingBook.isEmpty()) {
            return false;
        }

        return repository.update(id, book);
    }

    public boolean deleteBook(Long id) throws SQLException {
        Optional<Book> existingBook = repository.findById(id);
        if (existingBook.isEmpty()) {
            return false;
        }

        return repository.delete(id);
    }

    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN is required");
        }
        if (book.getLanguageId() == null) {
            throw new IllegalArgumentException("Language ID is required");
        }
        if (book.getNumPages() == null || book.getNumPages() <= 0) {
            throw new IllegalArgumentException("Number of pages must be greater than 0");
        }
        if (book.getPublicationDate() == null) {
            throw new IllegalArgumentException("Publication date is required");
        }
        if (book.getPublisherId() == null) {
            throw new IllegalArgumentException("Publisher ID is required");
        }
    }
}
