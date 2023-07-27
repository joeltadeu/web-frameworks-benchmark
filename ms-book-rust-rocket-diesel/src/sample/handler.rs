use std::env;

use diesel::result::Error;
use rocket::http::Status;
use rocket::response::status;
use rocket_contrib::json::Json;

use crate::connection::DbConn;
use crate::sample;
use crate::sample::model::Book;

#[get("/")]
pub fn all_books(connection: DbConn) -> Result<Json<Vec<Book>>, Status> {
    sample::repository::show_books(&connection)
        .map(|book| Json(book))
        .map_err(|error| error_status(error))
}

#[get("/<id>")]
pub fn get_book(id: i32, connection: DbConn) -> Result<Json<Book>, Status> {
    sample::repository::get_book(id, &connection)
        .map(|book| Json(book))
        .map_err(|error| error_status(error))
}

fn error_status(error: Error) -> Status {
    match error {
        Error::NotFound => Status::NotFound,
        _ => Status::InternalServerError
    }
}