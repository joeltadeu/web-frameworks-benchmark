package com.performance.book.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class MetricsController implements HttpHandler {

    private final CollectorRegistry registry;

    public MetricsController() {
        this.registry = CollectorRegistry.defaultRegistry;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        exchange.getResponseHeaders().set("Content-Type", TextFormat.CONTENT_TYPE_004);
        exchange.sendResponseHeaders(200, 0);

        try (OutputStream os = exchange.getResponseBody();
             OutputStreamWriter writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            TextFormat.write004(writer, registry.metricFamilySamples());
            writer.flush();
        }
    }
}
