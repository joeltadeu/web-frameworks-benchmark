package com.performance.book.repository;

import com.performance.book.config.DatabaseConfig;
import com.performance.book.model.Book;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    public Book save(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, isbn, language_id, num_pages, publication_date, publisher_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.setLong(3, book.getLanguageId());
            stmt.setInt(4, book.getNumPages());
            stmt.setDate(5, Date.valueOf(book.getPublicationDate()));
            stmt.setLong(6, book.getPublisherId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating book failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating book failed, no ID obtained.");
                }
            }
        }

        return book;
    }

    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, title, isbn, language_id, num_pages, publication_date, publisher_id " +
                "FROM book ORDER BY id ASC";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }

        return books;
    }

    public Optional<Book> findById(Long id) throws SQLException {
        String sql = "SELECT id, title, isbn, language_id, num_pages, publication_date, publisher_id " +
                "FROM book WHERE id = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToBook(rs));
                }
            }
        }

        return Optional.empty();
    }

    public boolean update(Long id, Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, isbn = ?, language_id = ?, " +
                "num_pages = ?, publication_date = ?, publisher_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.setLong(3, book.getLanguageId());
            stmt.setInt(4, book.getNumPages());
            stmt.setDate(5, Date.valueOf(book.getPublicationDate()));
            stmt.setLong(6, book.getPublisherId());
            stmt.setLong(7, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM book WHERE id = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setLanguageId(rs.getLong("language_id"));
        book.setNumPages(rs.getInt("num_pages"));
        book.setPublicationDate(rs.getDate("publication_date").toLocalDate());
        book.setPublisherId(rs.getLong("publisher_id"));

        return book;
    }
}
