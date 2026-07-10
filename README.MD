# Fitness Tracker

Fitness Tracker is a Spring Boot web application for tracking exercises and exercise logs per user.

## Table of contents
- [Overview](#overview)
- [Current features](#current-features)
- [Tech stack](#tech-stack)
- [Architecture and modules](#architecture-and-modules)
- [Prerequisites](#prerequisites)
- [Local setup](#local-setup)
- [Configuration](#configuration)
- [Database and migrations](#database-and-migrations)
- [Run the application](#run-the-application)
- [Test the application](#test-the-application)
- [Project structure](#project-structure)
- [Troubleshooting](#troubleshooting)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)

## Overview
The app provides a server-rendered UI (Thymeleaf) where authenticated users can:
- register and log in,
- manage exercises,
- create and view exercise logs,
- filter exercise logs by exercise.

Most application pages require authentication. Public pages include login, registration, and static assets.

## Current features
- User registration (`/register`) and form-based login (`/login`)
- Exercise management (`/fitness/exercises`)
- Exercise log management (`/fitness/exercise-log`)
- Exercise-log filtering by `exerciseId`
- Error page endpoint (`/error`)
- Flyway-based schema migrations for PostgreSQL and H2 test database

## Tech stack
- Java 21
- Spring Boot 3.4.1
- Spring MVC + Thymeleaf
- Spring Security
- Spring Data JPA
- PostgreSQL (main runtime database)
- H2 (tests)
- Flyway
- MapStruct
- Lombok
- Maven

## Architecture and modules
The application is organized by layered responsibilities:

- `controller/`: web endpoints and view rendering
  - `exercise/ExerciseController`
  - `exerciselog/ExerciseLogController`
  - `login/LoginController`
  - `register/RegistrationController`
  - `home/HomeController`
- `service/`: domain logic and DTO mapping
  - `exercise/ExerciseService`, `exercise/ExerciseMapper`
  - `exerciselog/ExerciseLogService`, `exerciselog/ExerciseLogMapper`
  - `fitnessuserdetails/FitnessUserDetailsService`
- `repository/`: JPA repositories for user/exercise/exercise-log persistence
- `model/`: entities and DTOs for exercise, exercise log, routine, and user domains
- `config/`: security and auth utilities (`SecurityConfig`, `AuthenticationUtils`)
- `src/main/resources/templates/`: Thymeleaf templates and fragments

## Prerequisites
- Java 21+
- Maven 3.6+
- PostgreSQL 13+ (local install or Docker)

## Local setup
1. Clone the repository:

```sh
git clone https://github.com/TheFluyter/fitnesstracker.git
cd fitnesstracker
```

2. Create a PostgreSQL database (default expected name: `fitness`).

3. Configure database credentials in `src/main/resources/application.properties` if needed:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/fitness
spring.datasource.username=postgres
spring.datasource.password=postgres
```

4. Build the project:

```sh
mvn clean install
```

### Optional: run PostgreSQL in Docker
If you do not have PostgreSQL locally, you can run it via Docker:

```sh
docker run --name fitness-postgres \
  -e POSTGRES_DB=fitness \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:13
```

## Configuration
Main settings are in:
- `src/main/resources/application.properties`

Current defaults include:
- PostgreSQL datasource (`spring.datasource.*`)
- Flyway enabled against PostgreSQL scripts in `classpath:/db/postgres`
- `spring.jpa.hibernate.ddl-auto=update`
- `spring.jpa.open-in-view=false`

Test settings are in:
- `src/test/resources/application.properties`

Tests use:
- H2 in-memory datasource
- Flyway scripts in `classpath:/db/h2`
- `spring.jpa.hibernate.ddl-auto=none`

## Database and migrations
Flyway migration scripts:
- PostgreSQL: `src/main/resources/db/postgres`
  - `V1__initial_database_setup.sql`
  - `V2__link_exercises_to_users.sql`
- H2 (tests): `src/main/resources/db/h2`
  - `V1__initial_database_setup.sql`
  - `V2__link_exercises_to_users.sql`

Integration test SQL fixtures are available in:
- `src/test/resources/integrationtest/sql`

## Run the application
Start in development mode:

```sh
mvn spring-boot:run
```

Then open:
- `http://localhost:8080`

Build and run the packaged jar:

```sh
mvn clean package
java -jar target/fitness-tracker-0.0.1-SNAPSHOT.jar
```

## Test the application
Run all tests:

```sh
mvn test
```

Run a specific test class:

```sh
mvn -Dtest=ExerciseControllerIntegrationTest test
```

## Project structure
```text
src/
  main/
    java/com/thefluyter/fitnesstracker/
      config/
      controller/
      exception/
      model/
      repository/
      service/
    resources/
      application.properties
      db/
        postgres/
        h2/
      templates/
      static/
  test/
    java/
    resources/
      application.properties
      integrationtest/sql/
```

## Troubleshooting
- `Connection refused` to PostgreSQL: verify PostgreSQL is running and port `5432` is available.
- Flyway migration failures: check DB credentials and ensure the configured `spring.flyway.locations` path exists.
- Login issues: create a user via `/register` first.
- Test failures tied to data setup: check SQL fixtures under `src/test/resources/integrationtest/sql`.

## Roadmap
Based on `TODO.MD`:
- Improve integration test data setup for newer tables
- Handle exercise re-use across users more robustly
- Expand exercise-log functionality
- Improve security configuration (CSRF and authorization hardening)
- Improve error logging/pages and credential feedback
- Planned feature: food tracker

## Contributing
Contributions are welcome. For now, please open an issue describing:
- what you want to change,
- why it is needed,
- and how you validated it.

## License
No `LICENSE` file is currently present in this repository. Add one if you want to define usage and distribution terms.
