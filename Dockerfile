# ---- Etapa de build ----
FROM maven:3-openjdk-17 AS build

ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"
ENV LANG=C.UTF-8

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src

# Compilamos la aplicación (sin tests)
RUN mvn clean package -DskipTests -Dmaven.test.skip=true

# ---- Etapa de runtime (ligera) ----
FROM openjdk:17-jdk-slim

# curl para el healthcheck del compose
RUN apt-get update && apt-get install -y --no-install-recommends curl ca-certificates && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
