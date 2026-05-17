# 🎓 Student Management System

A full-stack **Spring Boot MVC** application for managing student records with CRUD operations, image upload, validation, pagination, search functionality, and global exception handling.

The project follows a clean layered architecture using **Spring Boot**, **Spring MVC**, **Spring Data JPA**, **Thymeleaf**, and **MySQL**.

---

# 📖 Overview

The **Student Management System** allows users to:

- Add new students
- Update existing student details
- Delete student records
- Upload student profile images
- Search students by name
- View paginated student records
- Handle validation and exceptions gracefully

The application is designed to practice real-world Spring Boot development concepts including MVC architecture, file handling, validation, exception handling, and database integration.

---

# 🚀 Features

## 👨‍🎓 Student Management

- Add Student
- Update Student
- Delete Student
- View All Students

## 📸 Image Upload

- Upload student profile image
- Display uploaded images
- Optional image update support

## 🔍 Search Functionality

- Search students by name
- Dynamic search support

## 📄 Pagination

- Paginated student listing
- Improved data management

## ✅ Validation

- Server-side form validation
- Input error handling

## ⚠️ Global Exception Handling

- Custom exception handling
- Friendly error pages
- Centralized error management

## 🎨 Responsive UI

- Bootstrap 5 integration
- Responsive Thymeleaf templates

---

# 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring MVC | MVC Architecture |
| Spring Data JPA | Database Access |
| Hibernate | ORM Framework |
| Thymeleaf | Template Engine |
| MySQL | Relational Database |
| Bootstrap 5 | Frontend Styling |
| Maven | Dependency Management |

---

# 🧱 Architecture

The project follows layered MVC architecture:

```text
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Database
```

---

# 📂 Project Structure

```text
src/main/java/com/app
│
├── controller        # MVC Controllers
├── entity            # JPA Entities
├── repository        # Database Repositories
├── service           # Service Interfaces
├── service/impl      # Business Logic
├── exception         # Global Exception Handling
└── config            # Application Configuration

src/main/resources
│
├── static
│   └── uploads       # Uploaded Images
│
├── templates         # Thymeleaf Templates
│
└── application.properties
```

---

# ⚙️ Implemented Functionalities

## 👨‍🎓 Student Operations

| Feature | Description |
|---------|-------------|
| Add Student | Create new student records |
| Update Student | Modify student details |
| Delete Student | Remove student records |
| View Students | Display all students |

---

## 📸 File Upload Features

| Feature | Description |
|---------|-------------|
| Image Upload | Upload student profile images |
| Image Preview | Display uploaded images |
| Optional Update | Keep old image if new image not uploaded |

---

## 🔍 Search & Pagination

| Feature | Description |
|---------|-------------|
| Search Students | Search by student name |
| Pagination | Paginated student records |

---

# 🧾 Sample Student Entity

```java
public class Student {

    private Long id;

    private String name;

    private String email;

    private String course;

    private Integer age;

    private String imageName;
}
```

---

# 🔥 Core Concepts Implemented

The project includes several important Spring Boot concepts:

- MVC Architecture
- CRUD Operations
- File Upload Handling
- Thymeleaf Templating
- Pagination & Search
- Form Validation
- Global Exception Handling
- MySQL Database Integration
- Bootstrap Responsive Design
- Service Layer Abstraction
- Repository Pattern

---

# ❌ Exception Handling

Custom global exception handling implemented using:

```java
@ControllerAdvice
```

Handled scenarios include:

- Resource not found
- Validation errors
- File upload errors
- Internal server exceptions

---

# 🗄️ Database

The project uses **MySQL** as the relational database.

### Main Entity

- Student

---

# ▶️ Getting Started

## 1️⃣ Clone Repository

```bash
git clone https://github.com/shivanand-Ulgulwad/springboot-student-management-system.git
```

---

## 2️⃣ Configure Database

Create MySQL database:

```sql
CREATE DATABASE student_management_system;
```

---

## 3️⃣ Configure `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management_system
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 4️⃣ Run Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main class directly from your IDE.

---

# 🌐 Application URL

```text
http://localhost:8080/students
```

---

# 📌 Future Enhancements

- Spring Security Authentication
- Role-Based Authorization
- REST API Version
- Swagger Documentation
- Docker Support
- Unit Testing
- Cloud File Storage
- Student Dashboard Analytics

---

# 🧠 Learning Outcomes

This project helped in understanding:

- Spring Boot MVC Architecture
- Thymeleaf Integration
- CRUD Operations
- File Upload Handling
- Validation Techniques
- Exception Handling Best Practices
- Pagination & Search
- Database Integration with JPA
- Bootstrap UI Development

---

# 👨‍💻 Author

Developed as a portfolio project to practice enterprise-level Spring Boot MVC application development.

## 👤 Shivanand Ulgulwad

- GitHub: https://github.com/shivanand-Ulgulwad
- Repository: https://github.com/shivanand-Ulgulwad/springboot-student-management-system
