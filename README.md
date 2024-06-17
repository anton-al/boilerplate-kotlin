# Boilerplate for Kotlin (Spring Boot)

Web application for blog with tests and swagger (based on Spring Boot). 

Configuration file `BlogConfiguration.kt` populate db records. 
DB connection configure in `application.properties` file.

## Setup postgresDB

`docker-compose up -d` 

## Run application

`./gradlew clean bootRun`

## SWAGGER UI

http://localhost:8080/swagger-ui

## OpenApi Docs

http://localhost:8080/v3/api-docs

