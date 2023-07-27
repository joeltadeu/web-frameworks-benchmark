
diesel::table! {
    book (id) {
        id -> Integer,
        title -> Varchar,
        isbn -> Varchar,
        publication_date -> Date,
        language_id -> Integer,
        num_pages -> Integer,
        publisher_id -> Integer,
    }
}



