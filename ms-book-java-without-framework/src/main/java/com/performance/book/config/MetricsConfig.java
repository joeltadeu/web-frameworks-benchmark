package com.performance.book.config;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.hotspot.DefaultExports;

public class MetricsConfig {

    // Counter for total HTTP requests
    public static final Counter httpRequestsTotal = Counter.build()
            .name("http_requests_total")
            .help("Total number of HTTP requests")
            .labelNames("method", "endpoint", "status")
            .register();

    // Histogram for request duration
    public static final Histogram httpRequestDuration = Histogram.build()
            .name("http_request_duration_seconds")
            .help("HTTP request duration in seconds")
            .labelNames("method", "endpoint")
            .register();

    // Counter for database operations
    public static final Counter dbOperationsTotal = Counter.build()
            .name("db_operations_total")
            .help("Total number of database operations")
            .labelNames("operation", "table")
            .register();

    // Histogram for database query duration
    public static final Histogram dbQueryDuration = Histogram.build()
            .name("db_query_duration_seconds")
            .help("Database query duration in seconds")
            .labelNames("operation", "table")
            .register();

    // Gauge for active database connections
    public static final Gauge dbConnectionsActive = Gauge.build()
            .name("db_connections_active")
            .help("Number of active database connections")
            .register();

    // Counter for errors
    public static final Counter errorsTotal = Counter.build()
            .name("errors_total")
            .help("Total number of errors")
            .labelNames("type", "operation")
            .register();

    // Gauge for books in database
    public static final Gauge booksTotal = Gauge.build()
            .name("books_total")
            .help("Total number of books in the database")
            .register();

    public static void init() {
        // Register JVM metrics (memory, threads, GC, etc.)
        DefaultExports.initialize();
    }
}
