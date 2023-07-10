package com.performance.book;

import io.micronaut.runtime.Micronaut;

public class BookMicronautRestJdbcApplication {

    public static void main(String[] args) {
        Micronaut.run(BookMicronautRestJdbcApplication.class, args);
    }
}