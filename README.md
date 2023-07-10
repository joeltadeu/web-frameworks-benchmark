# Comparing Web Frameworks and Testing Performance

## Overview

The objective of this project was to compare the best known frameworks for java language such as Spring Boot, Micronaut and Quarkus, C# with .Net/API Core
creating an api for querying books using different forms of persistence such as JDBC, Hibernate and Reactive, Entity Framework.

It was also possible to carry out an extensive performance test using the Gatling tool simulating the simultaneous access of 100 to 500 users for 10 minutes.

Below is the list of created microservices:

| Microservice                                                           |   Framework   | Persistence      |
|------------------------------------------------------------------------|:-------------:|------------------|
| [ms-book-micronaut-reactive](ms-book-micronaut-reactive)               |   Micronaut   | Reactive         |
| [ms-book-micronaut-rest-jdbc](ms-book-micronaut-rest-jdbc)             |   Micronaut   | JDBC             |
| [ms-book-quarkus-reactive](ms-book-quarkus-reactive)                   |    Quarkus    | Reactive         |
| [ms-book-quarkus-rest-hibernate](ms-book-quarkus-rest-hibernate)       |    Quarkus    | Hibernate        |
| [ms-book-quarkus-rest-jdbc](ms-book-quarkus-rest-jdbc)                 |    Quarkus    | JDBC             |
| [ms-book-springboot-reactive](ms-book-springboot-reactive)             |  Spring Boot  | Reactive         |
| [ms-book-springboot-rest-hibernate](ms-book-springboot-rest-hibernate) |  Spring Boot  | Hibernate        |
| [ms-book-springboot-rest-jdbc](ms-book-springboot-rest-jdbc)           |  Spring Boot  | JDBC             |
| [ms-book-dotnet-entity-framework](ms-book-dotnet-entity-framework)     | .Net/API Core | Entity Framework |

Each project has its own docker-compose and can run independently.

Projects were also created to assist in the creation of the database, in the execution of performance tests and in the display of consolidated results.

Below is the list of projects and a brief description:

| Project                                          | Description                                                                                                                       | 
|--------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| [book-database](book-database)                   | Project responsible for creating the database used by microservices                                                               | 
| [book-deploy](book-deploy)                       | Project containing common files for prometheus and grafana configuration                                                          |
| [book-metabase](book-metabase)                   | Project responsible for creating the container with the metabase tool to display the comparative dashboard between the frameworks |
| [book-performance-tests](book-performance-tests) | Project responsible for running the performance tests using the Gatling tool                                                      |


## Comparing Analysis

### Compiled size

The .Net microservice with Entity Framework is the smallest compiled at 17.2 MB. The spring boot microservice with hibernate has the largest compiled with 43.66 MB, approximately 154% larger than the smallest.

![Alt text](books-assets/charts/compiled_size.png?raw=true "Compiled Size")

### Container Size

The .Net microservice with Entity Framework has the smallest container size at 118 MB. The quarkus microservice with hibernate has the largest container with 444 MB, approximately 276% larger than the smallest.

![Alt text](books-assets/charts/container_size.png?raw=true "Container Size")

### Used Memory - start container

In the graph below we have a comparison of the memory used when uploading the container of each microservice. .Net with Entity Framework has the lowest initial memory footprint at 66 MB. The spring boot microservice with hibernate had the highest memory consumption with 264.3 MB, approximately 300% higher than the lowest.

![Alt text](books-assets/charts/start_used_memory.png?raw=true "Used Memory - start container")

### Used Memory - 100 users in 10 min

In the graph below we have a comparison of memory usage simulating the access of 100 simultaneous users for 10 minutes. Spring boot with JDBC had the lowest memory footprint at 690 MB. The quarkus microservice with hibernate had the highest memory consumption with 4.63 GB, approximately 571% higher than the lowest consumption.

![Alt text](books-assets/charts/used_memory_100_users.png?raw=true "Frameworks x Used Memory - 100 users in 10 min")

### Used Memory - 500 users in 10 min

In the graph below we have a comparison of memory usage simulating the access of 500 simultaneous users for 10 minutes. Quarkus with Reactive had the lowest memory consumption at 400 MB but that's because it wasn't able to respond to requests. Thus, we consider that the Micronaut Reactive microservice had the lowest memory consumption with 2.19 GB. The quarkus microservice with jdbc had the highest memory consumption with 4.62 GB, approximately 110% higher than the lowest consumption.


![Alt text](books-assets/charts/used_memory_500_users.png?raw=true "Frameworks x Used Memory - 500 users in 10 min")

## Performance Tests


### Total of requests - 100 users in 10 min

In the graph below we have a comparison of the number of requests that each microservice was able to respond with 100 simultaneous users for 10 minutes. Spring-boot JDBC was able to respond to the most requests with a total of 12,566 while Micronaut Reactive was able to respond to the fewest requests with a total of 11,144. A difference of approximately 13%.


![Alt text](books-assets/charts/total_requests_100_users.png?raw=true "Total Requests - 100 users in 10 min")


### Total of requests - 500 users in 10 min

In the graph below we have a comparison of the number of requests that each microservice was able to respond with 500 simultaneous users for 10 minutes. Spring-boot hibernate was able to respond to the most requests with a total of 57,868 while Quarkus Reactive was able to respond to the fewest requests with a total of 19,214. A difference of approximately 201%.


![Alt text](books-assets/charts/total_requests_500_users.png?raw=true "Total Requests - 500 users in 10 min")

### Requests per second - 100 users in 10 min

In the graph below we have a comparison of the number of requests answered in one second simulating the access of 100 simultaneous users for 10 minutes. Spring-boot JDBC had the highest number of requests per second with 18.76 while micronaut reactive had the lowest with 16.61. A difference of approximately 13%.


![Alt text](books-assets/charts/requests_per_second_100_users.png?raw=true "Requests per second - 100 users in 10 min")

### Requests per second - 500 users in 10 min

In the graph below we have a comparison of the number of requests answered in one second simulating the access of 500 simultaneous users for 10 minutes. Spring-boot hibernate had the highest number of requests per second with 86.24 while quarkus reactive had the lowest with 28.13. A difference of approximately 207%.


![Alt text](books-assets/charts/requests_per_second_500_users.png?raw=true "Requests per second - 500 users in 10 min")

### Response time - 100 users in 10 min

In the graph below we have a comparison of the response time of each microservice simulating the access of 100 simultaneous users for 10 minutes. Spring-boot JDBC had the best average with 40 milliseconds while micronaut reactive had the worst average with 681 milliseconds. A difference of 1,603%.


![Alt text](books-assets/charts/response_time_100_users.png?raw=true "Response time - 100 users in 10 min")

### Response time - 500 users in 10 min

In the graph below we have a comparison of the response time of each microservice simulating the access of 500 simultaneous users for 10 minutes. Spring-boot Hibernate had the best average with 424 milliseconds while quarkus reactive had the worst average with 7,920 milliseconds. A difference of 1,768%.


![Alt text](books-assets/charts/response_time_500_users.png?raw=true "Response time - 500 users in 10 min")



