# 🛒 E-Commerce Microservices Application
This is a robust, scalable, and production-ready e-commerce application built using modern microservices architecture. The project demonstrates the power of distributed systems using Spring Boot, Apache Kafka, Docker, and several essential Spring Cloud frameworks.

## 🔧 Tech Stack
* Java 17 and Spring Boot: Core application framework for creating standalone, production-grade services.
* Spring Cloud: For building robust microservices architecture, including:
* Spring Cloud Gateway – API gateway and routing
* Spring Cloud Config – Centralized external configuration
* Spring Cloud Netflix Eureka – Service discovery
* Apache Kafka: Message broker used for asynchronous communication between microservices, ensuring high performance and decoupled design.
* Docker: Containerized each service to allow seamless local and production deployments.
Spring Security + JWT: Secures services with JSON Web Token-based authentication.
* Spring Data JPA & Hibernate: Simplifies data persistence with relational databases.
* PostgreSQL: Used for data storage across various services.

## 📦 Microservices Included
* User Service: Handles user registration, authentication, and profile management.
* Product Service: Manages product catalog including CRUD operations.
* Order Service: Handles order placement and processing.
* Inventory Service: Tracks and updates inventory in real-time.
* Payment Service: Simulates payment processing workflows.
* Notification Service: Sends notifications (e.g., email, SMS) based on order or user actions.
* API Gateway: Central entry point for all client requests.
* Config Server: Manages centralized external configuration.
* Eureka Server: Service registry for microservices to discover each other.

## 🔄 Inter-Service Communication
* RESTful APIs: For direct service-to-service communication.
* Kafka Topics: For handling events such as:
* Order placed
* Inventory updated
Notification sent

