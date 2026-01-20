package com.performance.book.simulation;

import com.performance.book.config.SimulationConfig;
import com.performance.book.scenario.BookEndToEndScenario;
import io.gatling.javaapi.core.ClosedInjectionStep;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;
import static io.gatling.javaapi.http.HttpDsl.http;

public class BookLoadSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol = http.acceptHeader("application/json");

    {
        List<ClosedInjectionStep> steps = new ArrayList<>();

        steps.add(rampConcurrentUsers(0).to(SimulationConfig.getUsers()).during(60));
        steps.add(
                constantConcurrentUsers(SimulationConfig.getUsers())
                        .during(Duration.ofMinutes(SimulationConfig.getDurationMinutes())));

        setUp(BookEndToEndScenario.build().injectClosed(steps))
                .protocols(httpProtocol)
                .assertions(
                        global().failedRequests().percent().is(0.0),
                        global().responseTime().percentile(95).lt(800));
    }
}
