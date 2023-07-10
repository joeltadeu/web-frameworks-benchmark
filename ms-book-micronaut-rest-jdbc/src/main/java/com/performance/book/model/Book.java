package com.performance.book.model;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedEntity
public class Book {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    private String title;

    private String isbn;
    private Long languageId;
    private Integer numPages;
    private LocalDate publicationDate;
    private Long publisherId;
}
