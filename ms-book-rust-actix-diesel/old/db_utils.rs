use actix::{Actor, Addr, SyncContext};
use diesel::{
    MysqlConnection,
    r2d2::{ConnectionManager, Pool}
};

pub struct AppState {
    pub db: Addr<DbActor>
}

pub struct DbActor(pub Pool<ConnectionManager<MysqlConnection>>);

impl Actor for DbActor {
    type Context = SyncContext<Self>;
}

pub fn get_pool(db_url: &str) -> Pool<ConnectionManager<MysqlConnection>> {
    let manager: ConnectionManager<MysqlConnection> = ConnectionManager::<MysqlConnection>::new(db_url);
    Pool::builder().build(manager).expect("Error building a connection pool")
}