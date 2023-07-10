package com.performance.book.controller;

import com.performance.book.model.Book;
import com.performance.book.service.BookService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@RequestScoped
@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {

    private final BookService service;

    @Inject
    public BookController(BookService service) {
        this.service = service;
    }

    @GET
    @Path("/{id}")
    public Book findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    public List<Book> getUsers() {
        return service.findAll();
    }
}
