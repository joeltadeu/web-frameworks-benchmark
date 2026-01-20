# Build stage
FROM ghcr.io/graalvm/native-image-community:25 AS builder

# Install Maven
RUN curl -fsSL https://downloads.apache.org/maven/maven-3/3.9.12/binaries/apache-maven-3.9.12-bin.tar.gz | tar -xz -C /opt && \
    ln -s /opt/apache-maven-3.9.12/bin/mvn /usr/bin/mvn

WORKDIR /build

# Copy project files
COPY pom.xml ./
COPY src ./src/

# Build native image
RUN mvn clean package -Pnative native:compile -DskipTests

# Runtime stage
FROM alpine:3.19 AS runtime

# Install runtime dependencies for GraalVM native images
RUN apk add --no-cache gcompat libstdc++ zlib

WORKDIR /app

# Copy only the native executable
COPY --from=builder /build/target/ms-book-springboot-native ./app

EXPOSE 8080

# Use exec form with the full path
CMD ["/app/app"]