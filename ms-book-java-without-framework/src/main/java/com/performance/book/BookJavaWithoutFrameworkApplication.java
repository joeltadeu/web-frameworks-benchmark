package com.performance.book;

import com.performance.book.config.DatabaseConfig;
import com.performance.book.controller.BookController;
import com.performance.book.controller.MetricsController;
import com.performance.book.repository.BookRepository;
import com.performance.book.service.BookService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class BookJavaWithoutFrameworkApplication {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            var repository = new BookRepository();
            var service = new BookService(repository);

            server.createContext("/v1/books", new BookController(service));
            server.createContext("/metrics", new MetricsController());

            server.setExecutor(null);
            server.start();

            System.out.println("Book Microservice started on port " + PORT);
            System.out.println("Available endpoints:");
            System.out.println("  POST   /v1/books");
            System.out.println("  GET    /v1/books");
            System.out.println("  GET    /v1/books/{id}");
            System.out.println("  PUT    /v1/books/{id}");
            System.out.println("  DELETE /v1/books/{id}");
            System.out.println("  GET    /metrics");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\nShutting down server...");
                server.stop(0);
                DatabaseConfig.close();
                System.out.println("Server stopped.");
            }));

        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}