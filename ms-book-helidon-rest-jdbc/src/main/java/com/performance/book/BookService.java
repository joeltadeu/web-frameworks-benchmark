package com.performance.book;

import io.helidon.common.context.Contexts;
import io.helidon.common.media.type.MediaTypes;
import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.http.NotFoundException;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;

import java.lang.System.Logger;
import java.util.Map;

/**
 * An {@link HttpService} that uses {@link DbClient}.
 */
public class BookService implements HttpService {

    private static final Logger LOGGER = System.getLogger(BookService.class.getName());
    private static final JsonBuilderFactory JSON_FACTORY = Json.createBuilderFactory(Map.of());

    private final DbClient dbClient;

    /**
     * Create a new Pokémon service with a DB client.
     */
    BookService() {
        Config config = Config.global().get("db");
        this.dbClient = Contexts.globalContext()
                .get(DbClient.class)
                .orElseGet(() -> DbClient.create(config));

    }


    @Override
    public void routing(HttpRules rules) {
        rules.get("/", this::index)
                // List all Books
                .get("/books", this::listBooks)
                // Get Book by name
                .get("/books/{id}", this::getBookById);
    }

    /**
     * Return index page.
     *
     * @param request  the server request
     * @param response the server response
     */
    private void index(ServerRequest request, ServerResponse response) {
        response.headers().contentType(MediaTypes.TEXT_PLAIN);
        response.send("""
                Book JDBC Example:
                     GET /v1/books             - List all books
                     GET /v1/books/{id}        - Get book by id
                """);
    }


    /**
     * Return JsonArray with all stored Books.
     * Book object contains list of all type names.
     * This method is abstract because implementation is DB dependent.
     *
     * @param request  the server request
     * @param response the server response
     */
    private void listBooks(ServerRequest request, ServerResponse response) {
        JsonArray jsonArray = dbClient.execute().namedQuery("select-all-books")
                .map(row -> row.as(JsonObject.class))
                .collect(JSON_FACTORY::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::addAll)
                .build();
        response.send(jsonArray);
    }

    /**
     * Get a single Book by id.
     *
     * @param request  server request
     * @param response server response
     */
    private void getBookById(ServerRequest request, ServerResponse response) {
        int bookId = Integer.parseInt(request.path()
                .pathParameters()
                .get("id"));

        response.send(dbClient.execute().createNamedGet("select-book-by-id")
                .addParam("id", bookId)
                .execute()
                .orElseThrow(() -> new NotFoundException("Book " + bookId + " not found"))
                .as(JsonObject.class));
    }
}
