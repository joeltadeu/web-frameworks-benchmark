package com.performance.book.repository;

import com.performance.book.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class BookRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Book> findById(Long id) {
        String sql = """
                select * from `book` b where b.id = :id
                """;

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        return this.getBook(sql, namedParameters);
    }

    public List<Book> findAll() {
        String sql = """
                select * from `book`
                """;

        try {
            return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), BookRepository::mapRow);
        } catch (Exception e) {
            log.error(e);
            return Collections.emptyList();
        }
    }

    private Optional<Book> getBook(String sql, MapSqlParameterSource namedParameters) {
        try {
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(sql, namedParameters, BookRepository::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Book.builder()
                .id(rs.getLong("id"))
                .isbn(rs.getString("isbn"))
                .title(rs.getString("title"))
                .publicationDate(rs.getDate("publication_date").toLocalDate())
                .languageId(rs.getLong("language_id"))
                .publisherId(rs.getLong("publisher_id"))
                .numPages(rs.getInt("num_pages"))
                .build();
    }

}
