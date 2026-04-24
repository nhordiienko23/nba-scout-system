# Team Management System

A robust **Spring Boot** backend application designed for managing professional sports rosters and financial data. The project emphasizes clean architecture, API reliability, and effective data management using Object-Oriented Programming (OOP) principles.

## 🚀 Key Features

* **RESTful API**: Full CRUD operations for team management with sophisticated data filtering (e.g., salaries, rankings, experience, positions).
* **Data Persistence**: Reliable state management utilizing **Java Serialization** for file-based binary storage, integrated within a Spring service layer.
* **Error Handling**: Implemented **Global Exception Handling** (`@ControllerAdvice`) to ensure clean API responses (400, 404, etc.) and a "Fail-fast" validation approach for data integrity.
* **Optimized Data Management**: Utilizes efficient data structures (`HashMap`, `ArrayList`, `EnumSet`) for rapid data retrieval and multi-position player logic.
* **Clean Code**: Adherence to **SOLID** and **DRY** principles to ensure maintainability and scalability.

## 🛠 Tech Stack

* **Framework**: Spring Boot 3
* **Language**: Java 21
* **API Documentation**: Springdoc OpenAPI (Swagger UI)
* **Design Patterns**: DTO pattern, Service-Layer architecture, Global Exception Handling.

## 🚀 Quick Start

### Prerequisites
* **Java JDK 17** or higher
* **Maven**

### Installation & Run

1. Clone the repository: 
   ```bash
   git clone [https://github.com/nhordiienko23/team-management-system.git](https://github.com/nhordiienko23/team-management-system.git)

Navigate to the project directory:
```bash
cd team-management-system
```
Build the project:
```
mvn clean install
```
Run the application:
```
mvn spring-boot:run
```
Access the interactive API documentation at:
```
http://localhost:8080/swagger-ui.html
