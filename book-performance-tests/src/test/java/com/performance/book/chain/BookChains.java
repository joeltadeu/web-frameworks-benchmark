package com.performance.book.chain;

import com.performance.book.config.SimulationConfig;
import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class BookChains {
    private BookChains() {
    }

    public static ChainBuilder getDoctorById() {
        return exec(http("Get book by id")
                .get(SimulationConfig.getBookUrl() + "/v1/books/1")
                .check(status().is(200)))
                .pause(1);
    }

    public static ChainBuilder getAllDoctors() {
        return exec(http("Get all books")
                .get(SimulationConfig.getBookUrl() + "/v1/books")
                .check(status().is(200)))
                .pause(1);
    }
}
