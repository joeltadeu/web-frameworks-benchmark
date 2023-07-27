use crate::error_handler::CustomError;
use diesel::r2d2::ConnectionManager;
use lazy_static::lazy_static;
use r2d2;
use std::env;
use diesel::MysqlConnection;

type Pool = r2d2::Pool<ConnectionManager<MysqlConnection>>;
pub type DbConnection = r2d2::PooledConnection<ConnectionManager<MysqlConnection>>;

lazy_static! {
    static ref POOL: Pool = {
        let db_url = env::var("DATABASE_URL").expect("Database url not set");
        let manager = ConnectionManager::<MysqlConnection>::new(db_url);
        Pool::new(manager).expect("Failed to create db pool")
    };
}

pub fn init() {
    lazy_static::initialize(&POOL);
    let _conn = connection().expect("Failed to get db connection");
}

pub fn connection() -> Result<DbConnection, CustomError> {
    POOL.get()
        .map_err(|e| CustomError::new(500, format!("Failed getting db connection: {}", e)))
}