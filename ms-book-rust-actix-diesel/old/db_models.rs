#![allow(unused)]
#![allow(clippy::all)]

use chrono::NaiveDate;
use diesel::{Queryable};
use serde::{Serialize};

#[derive(Queryable, Debug, Serialize)]
pub struct Book {
    pub id: i32,
    pub title: String,
    pub isbn: String,
    #[serde(skip_serializing)]
    pub publication_date: NaiveDate,
    pub language_id: i32,
    pub num_pages: i32,
    pub publisher_id: i32
}