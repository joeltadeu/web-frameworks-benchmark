package com.performance.book.repository;

import com.performance.book.model.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository  implements PanacheRepository<Book> {
}
