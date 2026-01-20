package com.performance.book;

import io.helidon.logging.common.LogConfig;
import io.helidon.config.Config;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.common.context.Contexts;
import io.helidon.dbclient.DbClient;
import io.helidon.webserver.observe.ObserveFeature;
import io.helidon.webserver.observe.health.HealthObserver;
import io.helidon.dbclient.health.DbClientHealthCheck;

/**
 * The application main class.
 */
public class BookHelidonRestJdbcApplication {


    /**
     * Cannot be instantiated.
     */
    private BookHelidonRestJdbcApplication() {
    }


    /**
     * Application main entry point.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        
        // load logging configuration
        LogConfig.configureRuntime();

        // initialize global config from default configuration
        Config config = Config.create();
        Config.global(config);

        DbClient dbClient = DbClient.create(config.get("db"));
        Contexts.globalContext().register(dbClient);

        ObserveFeature observe = ObserveFeature.builder()
                .addObserver(HealthObserver.builder()
                        .addCheck(DbClientHealthCheck.create(dbClient, config.get("db.health-check")))
                        .build())
                .build();

        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(BookHelidonRestJdbcApplication::routing)
                .addFeature(observe)
                .build()
                .start();

        System.out.println("WEB server is up! http://localhost:" + server.port() + "/v1/books");

    }


    /**
     * Updates HTTP Routing.
     */
    static void routing(HttpRouting.Builder routing) {
        routing
               .register("/v1", new BookService());
    }
}