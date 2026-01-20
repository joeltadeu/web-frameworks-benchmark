FROM maven:3.9.5-amazoncorretto-21 AS builder
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/

RUN mvn -DskipTests package

FROM amazoncorretto:21.0.5-alpine3.19
COPY --from=builder /tmp/target/*.jar /app/app.jar

# Ports exposing
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
