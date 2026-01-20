package com.performance.book.controller;

import com.performance.book.config.MetricsConfig;
import com.performance.book.model.Book;
import com.performance.book.service.BookService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.prometheus.client.Histogram;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookController implements HttpHandler {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Start timing the request
        //Histogram.Timer timer = MetricsConfig.httpRequestDuration.labels(method, path).startTimer();
        int statusCode = 500;

        try {
            switch (method) {
                case "POST" -> statusCode = handlePost(exchange, path);
                case "GET" -> statusCode = handleGet(exchange, path);
                case "PUT" -> statusCode = handlePut(exchange, path);
                case "DELETE" -> statusCode = handleDelete(exchange, path);
                default -> {
                    sendResponse(exchange, 405, createErrorJson("Method not allowed"));
                    statusCode = 405;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Increment error counter
            MetricsConfig.errorsTotal.labels("exception", method).inc();
            sendResponse(exchange, 500, createErrorJson("Internal server error: " + e.getMessage()));
        } finally {
            // Record request duration
            //timer.observeDuration();
            // Increment request counter
            MetricsConfig.httpRequestsTotal.labels(method, path, String.valueOf(statusCode)).inc();
        }
    }

    private int handlePost(HttpExchange exchange, String path) throws IOException, SQLException {
        if (!path.equals("/v1/books")) {
            sendResponse(exchange, 404, createErrorJson("Not found"));
            return 404;
        }

        String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);

        try {
            Book book = parseBookFromJson(requestBody);
            Book createdBook = service.createBook(book);

            String responseJson = bookToJson(createdBook);
            sendResponse(exchange, 201, responseJson);
            return 201;
        } catch (IllegalArgumentException e) {
            MetricsConfig.errorsTotal.labels("validation", "POST").inc();
            sendResponse(exchange, 400, createErrorJson(e.getMessage()));
            return 400;
        }
    }

    private int handleGet(HttpExchange exchange, String path) throws IOException, SQLException {
        if (path.equals("/v1/books")) {
            List<Book> books = service.getAllBooks();
            String responseJson = booksToJson(books);
            sendResponse(exchange, 200, responseJson);
            return 200;
        } else if (path.matches("/v1/books/\\d+")) {
            Long id = extractIdFromPath(path);
            Optional<Book> book = service.getBookById(id);

            if (book.isPresent()) {
                String responseJson = bookToJson(book.get());
                sendResponse(exchange, 200, responseJson);
                return 200;
            } else {
                sendResponse(exchange, 404, createErrorJson("Book not found"));
                return 404;
            }
        } else {
            sendResponse(exchange, 404, createErrorJson("Not found"));
            return 404;
        }
    }

    private int handlePut(HttpExchange exchange, String path) throws IOException, SQLException {
        if (!path.matches("/v1/books/\\d+")) {
            sendResponse(exchange, 404, createErrorJson("Not found"));
            return 404;
        }

        Long id = extractIdFromPath(path);
        String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);

        try {
            Book book = parseBookFromJson(requestBody);
            boolean updated = service.updateBook(id, book);

            if (updated) {
                sendResponse(exchange, 200, "");
                return 200;
            } else {
                sendResponse(exchange, 404, createErrorJson("Book not found"));
                return 404;
            }
        } catch (IllegalArgumentException e) {
            MetricsConfig.errorsTotal.labels("validation", "PUT").inc();
            sendResponse(exchange, 400, createErrorJson(e.getMessage()));
            return 400;
        }
    }

    private int handleDelete(HttpExchange exchange, String path) throws IOException, SQLException {
        if (!path.matches("/v1/books/\\d+")) {
            sendResponse(exchange, 404, createErrorJson("Not found"));
            return 404;
        }

        Long id = extractIdFromPath(path);
        boolean deleted = service.deleteBook(id);

        if (deleted) {
            sendResponse(exchange, 204, "");
            return 204;
        } else {
            sendResponse(exchange, 404, createErrorJson("Book not found"));
            return 404;
        }
    }

    private Book parseBookFromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();

        Book book = new Book();
        book.setTitle(jsonObject.getString("title"));
        book.setIsbn(jsonObject.getString("isbn"));
        book.setLanguageId(jsonObject.getJsonNumber("languageId").longValue());
        book.setNumPages(jsonObject.getInt("numPages"));
        book.setPublicationDate(LocalDate.parse(jsonObject.getString("publicationDate")));
        book.setPublisherId(jsonObject.getJsonNumber("publisherId").longValue());

        return book;
    }

    private String bookToJson(Book book) {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("id", book.getId())
                .add("title", book.getTitle())
                .add("isbn", book.getIsbn())
                .add("languageId", book.getLanguageId())
                .add("numPages", book.getNumPages())
                .add("publicationDate", book.getPublicationDate().toString())
                .add("publisherId", book.getPublisherId());

        return builder.build().toString();
    }

    private String booksToJson(List<Book> books) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Book book : books) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                    .add("id", book.getId())
                    .add("title", book.getTitle())
                    .add("isbn", book.getIsbn())
                    .add("languageId", book.getLanguageId())
                    .add("numPages", book.getNumPages())
                    .add("publicationDate", book.getPublicationDate().toString())
                    .add("publisherId", book.getPublisherId());

            arrayBuilder.add(objectBuilder);
        }

        return arrayBuilder.build().toString();
    }

    private String createErrorJson(String message) {
        JsonObject error = Json.createObjectBuilder()
                .add("error", message)
                .build();
        return error.toString();
    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(statusCode, responseBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }
}
