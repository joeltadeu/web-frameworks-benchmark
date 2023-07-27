use actix_web::{
    get,
    web::{Data, Path},
    Responder, HttpResponse,
};
use crate::{
    messages::{FetchBook, FetchBooks},
    AppState, DbActor
};
use actix::Addr;

#[get("/v1/books")]
pub async fn fetch_books(state: Data<AppState>) -> impl Responder {

    let db: Addr<DbActor> = state.as_ref().db.clone();

    match db.send(FetchBooks).await {
        Ok(Ok(info)) => HttpResponse::Ok().json(info),
        Ok(Err(_)) => HttpResponse::NotFound().json("No books found"),
        _ => HttpResponse::InternalServerError().json("Unable to retrieve books"),
    }
}

#[get("/v1/books/{id}")]
pub async fn fetch_book_by_id(state: Data<AppState>, path: Path<i32>) -> impl Responder {
    let id: i32 = path.into_inner();

    let db: Addr<DbActor> = state.as_ref().db.clone();

    match db.send(FetchBook { book_id: id }).await {
        Ok(Ok(info)) => HttpResponse::Ok().json(info),
        Ok(Err(_)) => HttpResponse::NotFound().json(format!("No book found with id: {id}")),
        _ => HttpResponse::InternalServerError().json("Unable to retrieve book"),
    }
}