FROM maven:3.8.5-openjdk-17 AS builder
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/

RUN mvn clean package

FROM registry.access.redhat.com/ubi8/openjdk-17:1.14

ENV LANGUAGE='en_US:en'

WORKDIR /deployments/
RUN chown 185 /deployments \
    && chmod "g+rwX" /deployments \
    && chown 185:root /deployments

# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --chown=185:root target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185:root target/quarkus-app/*.jar /deployments/
COPY --chown=185:root target/quarkus-app/app/ /deployments/app/
COPY --chown=185:root target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080

USER 185

ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"
