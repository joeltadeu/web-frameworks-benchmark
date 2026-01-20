package com.performance.book.config;

import com.performance.book.simulation.BookLoadSimulation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SimulationConfig {

    private static final Properties FILE_PROPS = loadProperties();

    private static final String bookUrl = get("bookUrl", "http://localhost:8080");

    private static final int users = Integer.parseInt(get("users", "10"));

    private static final int durationMinutes = Integer.parseInt(get("durationMinutes", "5"));

    private static String get(String key, String defaultValue) {
        String sysValue = System.getProperty(key);
        if (sysValue != null && !sysValue.isBlank()) {
            return sysValue;
        }

        String fileValue = FILE_PROPS.getProperty(key);
        if (fileValue != null && !fileValue.isBlank()) {
            return fileValue;
        }

        return defaultValue;
    }

    public static int getDurationMinutes() {
        return durationMinutes;
    }

    public static int getUsers() {
        return users;
    }

    public static String getBookUrl() {
        return bookUrl;
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input =
                     BookLoadSimulation.class.getClassLoader().getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("application.properties not found in resources folder!");
            }
            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
        return props;
    }
}

