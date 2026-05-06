# Secure Issue Tracker API

This project is a Spring Boot backend application focused on building secure and well-structured APIs using JWT authentication and role-based access control.

The idea behind this system is simple — replicate a real-world workflow where teams manage tickets and track security-related issues, similar to how tools like Jira are used in production environments.

---

## What this project demonstrates

- Building secure REST APIs using Spring Boot
- Implementing authentication using JWT
- Role-based access control (ADMIN, DEVELOPER, VIEWER)
- Designing clean layered architecture (Controller → Service → Repository)
- Working with SQL Server using JPA/Hibernate
- Handling validation and exceptions properly
- Logging important application events for debugging and traceability

---

## Tech stack

- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA / Hibernate
- Microsoft SQL Server
- Maven
- Swagger (OpenAPI)

---

## Features

### Authentication
- User registration and login
- JWT token generation and validation

### Tickets
- Create, update, fetch, and delete tickets
- Partial updates supported
- Role-based restrictions applied

### Security Issues
- Track vulnerabilities (authorization, authentication, etc.)
- Assign severity and affected endpoints
- Resolve issues with fix descriptions

### User Management
- View users
- Update roles (admin only)
- Fetch current logged-in user

---

## Roles and access

- **ADMIN**  
  Full control over users, tickets, and security issues

- **DEVELOPER**  
  Can view and update tickets, and resolve security issues

- **VIEWER**  
  Read-only access

---

## Running the project

### 1. Clone the repo

```bash
git clone <your-repo-link>
cd secure-issue-tracker-api