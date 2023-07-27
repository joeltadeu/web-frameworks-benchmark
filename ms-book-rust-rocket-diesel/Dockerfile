# https://github.com/rust-lang-nursery/docker-rust-nightly/blob/master/nightly/Dockerfile
FROM buildpack-deps:stretch as builder

ENV RUSTUP_HOME=/usr/local/rustup \
    CARGO_HOME=/usr/local/cargo \
    PATH=/usr/local/cargo/bin:$PATH

RUN set -eux; \
    url="https://static.rust-lang.org/rustup/dist/x86_64-unknown-linux-gnu/rustup-init"; \
    wget "$url"; \
    chmod +x rustup-init; \
    ./rustup-init -y --no-modify-path --default-toolchain nightly; \
    rm rustup-init; \
    chmod -R a+w $RUSTUP_HOME $CARGO_HOME; \
    rustup --version; \
    cargo --version; \
    rustc --version;

RUN USER=root cargo new --bin ms-book-rust-rocket-diesel
WORKDIR /ms-book-rust-rocket-diesel
COPY ./Cargo.toml ./Cargo.toml
RUN cargo build

RUN rm src/*.rs
COPY ./src ./src
COPY ./diesel.toml ./diesel.toml
RUN rm ./target/debug/deps/ms_book_rust_rocket_diesel*
RUN cargo build -v

FROM buildpack-deps:stretch

COPY --from=builder /ms-book-rust-rocket-diesel/target/debug/ms-book-rust-rocket-diesel /app/

ENTRYPOINT [ "/app/ms-book-rust-rocket-diesel" ]