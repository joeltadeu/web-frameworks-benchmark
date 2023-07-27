#![allow(proc_macro_derive_resolution_fallback)]

use crate::schema::book;
use chrono::NaiveDate;

#[derive(Queryable, AsChangeset, Serialize, Deserialize, Debug)]
#[table_name = "book"]
pub struct Book {
    pub id: i32,
    pub title: String,
    pub isbn: String,
    pub publication_date: NaiveDate,
    pub language_id: i32,
    pub num_pages: i32,
    pub publisher_id: i32
}

