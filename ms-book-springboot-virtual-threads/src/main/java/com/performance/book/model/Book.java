package com.performance.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String isbn;
    private Long languageId;
    private Integer numPages;
    private LocalDate publicationDate;
    private Long publisherId;
}
