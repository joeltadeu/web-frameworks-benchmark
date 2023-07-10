# book-database

## Overview

Microservice responsible to create the database using Flyway. 

## Entity Relationship Diagram (DER)
![Alt text](../books-assets/der/books-schema.png?raw=true "Books DER")

> :information_source: Project to create the book database [book-database](../book-database)

## Build & Run

### Local

```bash
mvn clean install
```
Execute o comando abaixo para rodar a aplicação.
```bash
java -jar book-database.jar
```

### Docker

to build
```
docker build -f Dockerfile -t book-database:1.0.0 .
```

to run as a container
```
docker run -d -p 8080:8080  -i -t book-database:1.0.0
```
## Flyway Commands

Migrates a database schema to the current version

```bash
mvn clean flyway:migrate -Dflyway.configFiles=flyway-{ENVIROMNENT}.conf
```
Prints current status/version of a database schema
```bash
mvn clean flyway:info -Dflyway.configFiles=flyway-{ENVIROMNENT}.conf
```

### Port

8080


## VERSIONS

### 1.0.0

- Spring-Boot 3.0.7
- Spring Data Jdbc
- Flyway
- Java 17
- MySQL 8