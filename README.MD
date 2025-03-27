# Fitness Tracker

## Overview
Fitness Tracker is a Spring Boot application designed to manage and track exercises. It provides functionalities to add, view, and manage exercise data.

## Technologies Used
- Java
- Spring Boot
- SQL
- Maven

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6.0 or higher
- PostGreSQL 13 or higher (locally or via docker image: `docker pull postgres`)

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/TheFluyter/fitnesstracker.git
    cd fitnesstracker
    ```

2. Configure the database connection in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/fitness
    spring.datasource.username=username
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage
- Access the application at `http://localhost:8080` to view the homepage.