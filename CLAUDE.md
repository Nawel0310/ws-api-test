# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot 3.5.6 application for integrating with the WhatsApp Business API (Meta Graph API v17.0). Java 17 Maven project.

## Architecture

Standard Spring Boot layered architecture:
- **Service Layer**: `src/main/java/com/example/WhatsApp/API/Test/service/`
  - `ApiWhatsAppService`: Initializes RestClient for WhatsApp Graph API with credentials from application.properties
- **Package structure**: `controller/`, `entity/`, `repository/`, `service/` directories exist but most are currently empty

## Configuration

WhatsApp API credentials stored in `src/main/resources/application.properties`:
- `whatsapp.identificador`: WhatsApp Business Account ID
- `whatsapp.token`: Access token for Meta Graph API

The `ApiWhatsAppService` constructor reads these properties and configures a RestClient pointing to `https://graph.facebook.com/v17.0/{identificador}/messages`.

## Common Commands

### Build and run
```bash
./mvnw clean install
./mvnw spring-boot:run
```

### Run tests
```bash
./mvnw test
```

### Run single test
```bash
./mvnw test -Dtest=ClassName#methodName
```

### Package application
```bash
./mvnw package
```

## Dependencies

- Spring Boot Data JPA (MySQL connector included)
- Spring Boot Validation
- Spring Boot Web (includes RestClient)
- Lombok (annotation processor configured in pom.xml)
- Spring Security Test (testing only)

## Database

Project configured for MySQL (connector present), but database configuration not yet set in application.properties. JPA entities would go in `entity/` package, repositories in `repository/`.