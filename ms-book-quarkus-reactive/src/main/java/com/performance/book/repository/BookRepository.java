package com.performance.book.repository;

import com.performance.book.model.Book;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class BookRepository {

    @Inject
    io.vertx.mutiny.mysqlclient.MySQLPool client;

    public Multi<Book> findAll() {
        Uni<RowSet<Row>> rowSet = client.query("SELECT * FROM book").execute();
        return rowSet.onItem().transformToMulti(set -> Multi.createFrom().iterable(set)).onItem().transform(Book::from);
    }

    public Uni<Book> findById(Long id) {
        return client.preparedQuery("SELECT * FROM book WHERE id=?").execute(Tuple.of(id))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? Book.from(iterator.next()) : null);
    }
}
