Student Management System

A full-stack Spring Boot MVC application for managing student records with CRUD operations, authentication, pagination, validation, search functionality, and image upload support.

Features
Student CRUD Operations
Pagination
Search Functionality
File/Image Upload
Form Validation
Global Exception Handling
Responsive UI using Bootstrap
Thymeleaf Template Engine
MySQL Database Integration

Tech Stack
Java 21
Spring Boot
Spring MVC
Spring Data JPA
Thymeleaf
MySQL
Bootstrap 5
Hibernate
Maven

Project Structure
src/
 └── main/
      ├── java/
      │     └── com.app
      └── resources/
            ├── static/
            │     └── uploads/
            ├── templates/
            └── application.properties
            
Setup Instructions

1. Clone Repository
git clone https://github.com/shivanand-Ulgulwad/springboot-student-management-system.git

3. Create MySQL Database
CREATE DATABASE student_management_system;

5. Configure application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management_system
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
4. Run the Project

Using Maven:

mvn spring-boot:run

Or run the main class directly from IDE.

Application Features Overview
Student Management
Add Student
Update Student
Delete Student
View Students
File Upload
Upload student profile image
Optional image update support
Validation
Server-side form validation
Error handling with custom exception handling
Search & Pagination
Search students by name
Paginated student records



Shivanand Ulgulwad

GitHub:
springboot-student-management-system
