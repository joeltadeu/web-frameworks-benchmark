FROM maven:3.9.12-eclipse-temurin-25-alpine AS builder

WORKDIR /tmp

# Copy dependency descriptor first to leverage Docker layer caching
COPY pom.xml ./

# Copy source code
COPY src ./src/

# Build with optimizations
RUN mvn clean package -DskipTests -B

FROM gcr.io/distroless/java25-debian13 AS runtime

COPY --from=builder /tmp/target/ms-book*.jar /app/app.jar

ARG PROFILE

# Ports exposing
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
