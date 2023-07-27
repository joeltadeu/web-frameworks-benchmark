use actix::SyncArbiter;
use actix_web::{web::Data, App, HttpServer};
use dotenv::dotenv;
use diesel::{
    r2d2::{ConnectionManager, Pool},
    MysqlConnection
};
use std::env;

mod services;
mod db_utils;
mod messages;
mod actors;
mod db_models;
mod schema;

use db_utils::{get_pool, AppState, DbActor};
use services::{fetch_books, fetch_book_by_id};

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    dotenv().ok();
    let db_url: String = env::var("DATABASE_URL").expect("DATABASE_URL must be set");
    let pool: Pool<ConnectionManager<MysqlConnection>> = get_pool(&db_url);
    let db_addr = SyncArbiter::start(5, move || DbActor(pool.clone()));

    HttpServer::new(move || {
        App::new()
            .app_data(Data::new(AppState { db: db_addr.clone() }))
            .service(fetch_books)
            .service(fetch_book_by_id)
    })
        .bind(("0.0.0.0", 8080))?
        .run()
        .await
}