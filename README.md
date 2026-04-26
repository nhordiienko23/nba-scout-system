# Team Management System

A robust Spring Boot backend application designed for managing professional sports rosters and financial data. The project emphasizes clean architecture, API reliability, and effective data management using Object-Oriented Programming (OOP) principles, now fully integrated with PostgreSQL and Docker.

## 🚀 Key Features

* RESTful API: Full CRUD operations for team management with sophisticated data filtering (salaries, rankings, experience, positions).
* Data Persistence: Reliable storage utilizing PostgreSQL with Spring Data JPA.
* Containerization: Fully dockerized environment with PostgreSQL and application services orchestration via Docker Compose.
* Error Handling: Global Exception Handling (@ControllerAdvice) for standardized API responses and data integrity validation.
* API Documentation: Integrated Swagger UI for interactive API testing.

## 🛠 Tech Stack

* Framework: Spring Boot 3
* Language: Java 21
* Database: PostgreSQL 16
* Orchestration: Docker & Docker Compose
* Documentation: Springdoc OpenAPI (Swagger UI)

## 🚀 Quick Start

### Prerequisites
* Docker & Docker Compose
* Java 21
* Maven

### Installation & Run

1. Clone the repository:
   ```
   git clone [https://github.com/nhordiienko23/team-management-system.git](https://github.com/nhordiienko23/team-management-system.git)
   cd team-management-system
    ```
2. Build the project:
   ```
   mvn clean package
   ```
3. Run the entire infrastructure (Database + App):
    ```
   docker-compose up --build
   ```
4. Access the interactive API documentation at:  
     ```
   http://localhost:8080/swagger-ui.html
   ```
## 🏗 Project Architecture
* docker-compose.yml: Defines the services (PostgreSQL db and the Spring Boot app) and manages networking.
* Dockerfile: Defines the multi-stage build process for the application image.
* application.yml: Centralized configuration supporting both local and Docker environments through environment variables.
