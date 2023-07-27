use crate::db_models::Book;
use crate::db_utils::DbActor;
use crate::schema::book::dsl::*;
use crate::messages::{FetchBook, FetchBooks};
use actix::Handler;
use diesel::{self, prelude::*};

impl Handler<FetchBooks> for DbActor {
    type Result = QueryResult<Vec<Book>>;

    fn handle(&mut self, _msg: FetchBooks, _ctx: &mut Self::Context) -> Self::Result {
        let mut conn = self.0.get().expect("Fetch Books: Unable to establish connection");

        book.get_results::<Book>(&mut conn)
    }
}

impl Handler<FetchBook> for DbActor {
    type Result = QueryResult<Book>;

    fn handle(&mut self, msg: FetchBook, _ctx: &mut Self::Context) -> Self::Result {
        let mut conn = self.0.get().expect("Fetch Book: Unable to establish connection");

        book.find(msg.book_id).get_result::<Book>(&mut conn)
    }
}