package com.performance.book;

import java.util.Optional;

import io.helidon.common.Weight;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.spi.DbMapperProvider;

/**
 * {@link java.util.ServiceLoader} provider implementation for DB mapper provider.
 */
@Weight(100)
public class BookMapperProvider implements DbMapperProvider {
    private static final BookMapper MAPPER = new BookMapper();

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<DbMapper<T>> mapper(Class<T> type) {
        if (type.equals(Book.class)) {
            return Optional.of((DbMapper<T>) MAPPER);
        }
        return Optional.empty();
    }
}
