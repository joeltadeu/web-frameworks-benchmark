package com.performance.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class BookSpringbootReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookSpringbootReactiveApplication.class, args);
    }

}
