package com.performance.book.repository;

import com.performance.book.model.Book;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@R2dbcRepository(dialect = Dialect.MYSQL)
public interface BookRepository extends ReactiveStreamsCrudRepository<Book, Long> {

    @NonNull
    @Override
    Mono<Book> findById(@NonNull @NotNull Long id);

    @NonNull
    @Override
    Flux<Book> findAll();
}
