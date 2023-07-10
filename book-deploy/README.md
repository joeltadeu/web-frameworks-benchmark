# book-deploy

## Overview

Run all the microservices using docker-compose

## Services Available

| Service                           | Port  | Description                                                                                                                               |
|:----------------------------------|:------|:------------------------------------------------------------------------------------------------------------------------------------------|
| ms-book-springboot-rest-jdbc      | 9080  | Microservice responsible for authentication and token generation for the Booking App product                                              |
| ms-book-springboot-rest-hibernate | 9081  | Microservice responsible for manage hotels and rooms and perform basic operations such as creating, updating, list of hotels and deletion |
| ms-book-springboot-reactive       | 9082  | Microservice responsible for manage hotels and rooms and perform basic operations such as creating, updating, list of hotels and deletion |
| ms-book-quarkus-rest-jdbc         | 9083  | Microservice responsible for manage hotels and rooms and perform basic operations such as creating, updating, list of hotels and deletion |
| ms-book-quarkus-rest-hibernate    | 9084  | Microservice responsible for manage hotels and rooms and perform basic operations such as creating, updating, list of hotels and deletion |
| ms-book-quarkus-reactive          | 9085  | Microservice responsible for manage hotels and rooms and perform basic operations such as creating, updating, list of hotels and deletion |
| ms-book-micronaut-rest-jdbc       | 9086  | Microservice responsible for manage hotels and rooms and perform basic operations such as creating, updating, list of hotels and deletion |
| ms-book-micronaut-reactive        | 9087  | Microservice responsible for manage hotels and rooms and perform basic operations such as creating, updating, list of hotels and deletion |
| MySQL 8.0                         | 3307  | Database used to store info about coverages and cub price                                                                                 |


## Build & Run

### Build

In the main folder build the projects using the command bellow:

For build maven projects

```bash
mvn clean install
```
For build gradle projects

```bash
./gradlew clean build
```

### Run

Run all the services using the command bellow:

```bash
docker-compose up
```

### Stop

Stop all the services using the command bellow:

```bash
docker-compose down
```

## Version

### 1.0.0

- docker compose
- docker

## License
Apache License v2.0