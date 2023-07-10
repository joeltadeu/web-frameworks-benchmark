package com.performance.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Book {

    @Id
    private Long id;

    private String title;

    private String isbn;
    private Long languageId;
    private Integer numPages;
    private LocalDate publicationDate;
    private Long publisherId;
}
