use crate::db_models::{Book};
use actix::Message;
use diesel::QueryResult;

#[derive(Message)]
#[rtype(result = "QueryResult<Vec<Book>>")]
pub struct FetchBooks;

#[derive(Message)]
#[rtype(result = "QueryResult<Book>")]
pub struct FetchBook {
    pub book_id: i32,
}