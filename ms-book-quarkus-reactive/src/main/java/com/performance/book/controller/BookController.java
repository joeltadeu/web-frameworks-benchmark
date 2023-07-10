package com.performance.book.controller;

import com.performance.book.model.Book;
import com.performance.book.service.BookService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Uni<Response> findById(@PathParam("id") Long id) {
        return service.findById(id)
                .onItem().transform(book -> book != null ? Response.ok(book) : Response.status(Response.Status.NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @GET
    public Multi<Book> getUsers() {
        return service.findAll();
    }
}
