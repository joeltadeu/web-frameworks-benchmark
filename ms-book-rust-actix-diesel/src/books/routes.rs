use crate::books::{Books};
use crate::error_handler::CustomError;
use actix_web::{get, web, HttpResponse};

#[get("/v1/books")]
async fn find_all() -> Result<HttpResponse, CustomError> {
    let books = web::block(|| Books::find_all()).await.unwrap();
    Ok(HttpResponse::Ok().json(books))
}

#[get("/v1/books/{id}")]
async fn find(id: web::Path<i32>) -> Result<HttpResponse, CustomError> {
    let book = Books::find(id.into_inner())?;
    Ok(HttpResponse::Ok().json(book))
}

pub fn init_routes(config: &mut web::ServiceConfig) {
    config.service(find_all);
    config.service(find);
}