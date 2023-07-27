use rocket;

use crate::connection;
use crate::sample;


pub fn create_routes() {
    rocket::ignite()
        .manage(connection::init_pool())
        .mount("/v1/books",
               routes![
                    sample::handler::all_books,
                    sample::handler::get_book
                    ],
        ).launch();
}