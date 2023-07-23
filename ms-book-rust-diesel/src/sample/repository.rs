#![allow(proc_macro_derive_resolution_fallback)]

use diesel;
use diesel::prelude::*;

use crate::sample::model::Book;

use crate::schema::book;
use crate::schema::book::dsl::*;

pub fn show_books(connection: &MysqlConnection) -> QueryResult<Vec<Book>>  {
    book.load::<Book>(&*connection)
}

pub fn get_book(book_id: i32, connection: &MysqlConnection) -> QueryResult<Book> {
    book::table.find(book_id).get_result::<Book>(connection)
}