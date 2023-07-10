package com.performance.book.repository;


import com.performance.book.model.Book;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {

    @Inject
    AgroalDataSource dataSource;

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM book")) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    books.add(new Book(rs.getLong("id"), rs.getString("title"), rs.getString("isbn"),
                            rs.getLong("language_id"), rs.getInt("num_pages"),
                            rs.getDate("publication_date").toLocalDate(), rs.getLong("publisher_id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Optional<Book> findById(Long id) {

        Book book = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM book");) {
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                book = new Book(rs.getLong("id"), rs.getString("title"), rs.getString("isbn"),
                        rs.getLong("language_id"), rs.getInt("num_pages"),
                        rs.getDate("publication_date").toLocalDate(), rs.getLong("publisher_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(book);
    }
}
