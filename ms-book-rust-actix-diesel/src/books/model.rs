use crate::db;
use crate::error_handler::CustomError;
use crate::schema::book;
use diesel::prelude::*;
use serde::{Deserialize, Serialize};
use chrono::NaiveDate;

#[derive(Serialize, Deserialize, AsChangeset, Insertable)]
#[table_name = "book"]
pub struct Book {
    pub id: i32,
    pub title: String,
    pub isbn: String,
    #[serde(skip_serializing)]
    pub publication_date: NaiveDate,
    pub language_id: i32,
    pub num_pages: i32,
    pub publisher_id: i32,
}

#[derive(Serialize, Deserialize, Queryable)]
pub struct Books {
    pub id: i32,
    pub title: String,
    pub isbn: String,
    #[serde(skip_serializing)]
    pub publication_date: NaiveDate,
    pub language_id: i32,
    pub num_pages: i32,
    pub publisher_id: i32,
}

impl Books {
    pub fn find_all() -> Result<Vec<Self>, CustomError> {
        let conn = db::connection()?;
        let books = book::table.load::<Books>(&conn)?;
        Ok(books)
    }

    pub fn find(id: i32) -> Result<Self, CustomError> {
        let conn = db::connection()?;
        let book = book::table.filter(book::id.eq(id)).first(&conn)?;
        Ok(book)
    }
}

