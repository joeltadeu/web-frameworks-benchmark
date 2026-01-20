FROM maven:3.9.12-eclipse-temurin-25-alpine AS builder

COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/

RUN mvn clean package

FROM gcr.io/distroless/java25-debian13 AS runtim

ENV LANGUAGE='en_US:en'

# Copy from the builder stage, not from local target/
COPY --from=builder /tmp/target/quarkus-app/lib/ /deployments/lib/
COPY --from=builder /tmp/target/quarkus-app/*.jar /deployments/
COPY --from=builder /tmp/target/quarkus-app/app/ /deployments/app/
COPY --from=builder /tmp/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080

# Add the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/deployments/quarkus-run.jar"]
