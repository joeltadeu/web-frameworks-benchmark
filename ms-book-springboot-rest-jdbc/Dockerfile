FROM maven:3.8.5-openjdk-17 AS builder
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/

RUN mvn -DskipTests package

FROM openjdk:17-alpine3.14
COPY --from=builder /tmp/target/*.jar /app/app.jar

ARG PROFILE

# Ports exposing
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
