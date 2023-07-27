table! {
    book (id) {
        id -> Int4,
        title -> Varchar,
        isbn -> Varchar,
        publication_date -> Date,
        language_id -> Int4,
        num_pages -> Int4,
        publisher_id -> Int4,
    }
}