package com.performance.book.scenario;

import com.performance.book.chain.BookChains;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.scenario;

public class BookEndToEndScenario {
    private BookEndToEndScenario() {
    }

    public static ScenarioBuilder build() {
        return scenario("Book End-to-End Scenario")
                .exec(BookChains.getDoctorById())
                .exec(BookChains.getAllDoctors());
    }
}
