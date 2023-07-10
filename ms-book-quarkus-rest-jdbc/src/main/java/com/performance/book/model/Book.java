package com.performance.book.model;

import java.time.LocalDate;

public class Book {

    private final Long id;
    private final String title;
    private final String isbn;
    private final Long languageId;
    private final Integer numPages;
    private final LocalDate publicationDate;
    private final Long publisherId;

    public Book(Long id, String title, String isbn, Long languageId, Integer numPages, LocalDate publicationDate,
                Long publisherId) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.languageId = languageId;
        this.numPages = numPages;
        this.publicationDate = publicationDate;
        this.publisherId = publisherId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Long getPublisherId() {
        return publisherId;
    }
}
