package com.performance.book;

import java.time.LocalDate;

public record Book(Long id, String title, String isbn, Long languageId, Integer numPages, LocalDate publicationDate, Long publisherId) {
}
