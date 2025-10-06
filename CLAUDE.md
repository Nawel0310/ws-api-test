# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot 3.5.6 application for integrating with the WhatsApp Business API (Meta Graph API v17.0). Java 17 Maven project.

## Architecture

Standard Spring Boot layered architecture:
- **Controller Layer**: `ApiWhatsAppController` exposes REST endpoint at `/api/v1/whatsapp/enviar` (POST) that accepts `MessageBodyDTO` (number, message)
- **Service Layer**: `ApiWhatsAppService` handles WhatsApp API integration
  - Constructor initializes RestClient with credentials from environment variables (`WHATSAPP_IDENTIFICADOR`, `WHATSAPP_TOKEN`)
  - `sendMessage()` transforms DTO → `RequestMessage` entity → sends to Graph API → parses `ResponseWhatsapp`
  - RestClient configured with base URL `https://graph.facebook.com/v17.0/{identificador}/messages`
- **Entity/DTO packages**: Request/Response models for WhatsApp API communication
  - `RequestMessage`: Contains `messaging_product`, `to`, `text` (RequestMessageText)
  - `ResponseWhatsapp`: Parses Graph API response
  - `MessageBodyDTO`: Controller input (number, message)

## Configuration

WhatsApp API credentials are read from **environment variables** (not application.properties):
- `WHATSAPP_IDENTIFICADOR`: WhatsApp Business Account ID
- `WHATSAPP_TOKEN`: Meta Graph API access token

These must be set in the environment before running the application.

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

- Spring Boot Web (RestClient for HTTP calls)
- Spring Boot Data JPA + MySQL connector (configured but not yet used)
- Spring Boot Validation
- Lombok (annotation processing configured in maven-compiler-plugin)
- Spring Security Test (testing scope only)

## Notes

- Database configuration not yet implemented despite JPA/MySQL dependencies
- `ApiWhatsAppService.sendMessage()` has comment "AGREGAR URI PERSONALIZADO" at line 34 - URI parameter is currently empty string
- Response entity classes (`ResponseWhatsappContact`, `ResponseWhatsappMessage`) exist but aren't fully integrated