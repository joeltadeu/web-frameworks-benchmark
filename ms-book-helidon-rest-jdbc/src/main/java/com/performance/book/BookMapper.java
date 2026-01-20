package com.performance.book;

import io.helidon.dbclient.DbColumn;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.DbRow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maps database statements to {@link com.performance.book.Book} class.
 */
public class BookMapper implements DbMapper<Book> {

    @Override
    public Book read(DbRow row) {
        DbColumn id = row.column("id");
        DbColumn title = row.column("title");
        DbColumn isbn = row.column("isbn");
        DbColumn languageId = row.column("language_id");
        DbColumn numPages = row.column("num_pages");
        DbColumn publicationDate = row.column("publication_date");
        DbColumn publisherId = row.column("publisher_id");
        return new Book(id.get(Long.class), title.get(String.class), isbn.get(String.class), languageId.get(Long.class), numPages.get(Integer.class), publicationDate.get(LocalDate.class), publisherId.get(Long.class));
    }

    @Override
    public Map<String, Object> toNamedParameters(Book value) {
        Map<String, Object> map = new HashMap<>(7);
        map.put("id", value.id());
        map.put("title", value.title());
        map.put("isbn", value.isbn());
        map.put("language_id", value.languageId());
        map.put("num_pages", value.numPages());
        map.put("publication_date", value.publicationDate());
        map.put("publisher_id", value.publisherId());
        return map;
    }

    @Override
    public List<Object> toIndexedParameters(Book value) {
        List<Object> list = new ArrayList<>(7);
        list.add(value.id());
        list.add(value.title());
        list.add(value.isbn());
        list.add(value.languageId());
        list.add(value.numPages());
        list.add(value.publicationDate());
        list.add(value.publisherId());
        return list;
    }
}
