
# **Kitchensink Application Migration**

This project is a migrated version of the `kitchensink` JBoss application to a modern Spring Boot 3.1.0 platform. It includes updates to work with a MongoDB database and implements JWT-based security using Spring Security 6.0. The application also incorporates Thymeleaf for UI rendering and follows modern best practices for validations, exception handling, and unit testing.

---

## **Features**
1. **Modernized Backend**:
    - Migrated from JBoss to Spring Boot 3.1.0.
    - Java 21-based runtime.

2. **Database Migration**:
    - Replaced the relational database with MongoDB.

3. **Security**:
    - Implements JWT-based authentication and authorization using Spring Security 6.0.
    - Provides login endpoint and role-based access control.

4. **UI Enhancements**:
    - Thymeleaf templates for seamless server-side rendering.

5. **Development Best Practices**:
    - Input validations using Java Bean Validation (Jakarta).
    - Global exception handling.
    - Unit testing with Spring Boot Starter Test.

6. **Scalable Architecture**:
    - Modularized codebase for easy maintainability and extensibility.

---

## **Setup Instructions**

### **Prerequisites**
1. Install **Java 21**.
2. Install **Maven**.
3. Install and run a **MongoDB instance** (default URI: `mongodb://localhost:27017/kitchensink`).

### **Steps to Build and Run**
1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd kitchensink-springboot
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application:
    - **Thymeleaf UI**: [http://localhost:8080/users](http://localhost:8080/users)
    - **API Endpoints**: [http://localhost:8080/api/users](http://localhost:8080/api/users)
    - **Login Endpoint**: [http://localhost:8080/api/auth/login](http://localhost:8080/api/auth/login)

---

## **Authentication and Authorization**
- **Login**:
    - Endpoint: `POST /api/auth/login`
    - Request Body:
      ```json
      {
        "username": "user",
        "password": "password"
      }
      ```
    - Response:
      ```json
      {
        "token": "JWT_TOKEN"
      }
      ```

- **Using the JWT**:
    - Include the token in the `Authorization` header for secure API requests:
      ```
      Authorization: Bearer JWT_TOKEN
      ```

- **Default User Credentials**:
    - Username: `user`
    - Password: `password`

---

## **Directory Structure**
```
kitchensink-springboot
├── pom.xml                      # Maven dependencies
├── src/main
│   ├── java/com/example/kitchensink
│   │   ├── KitchensinkApplication.java  # Main application entry point
│   │   ├── model
│   │   │   └── User.java                # MongoDB User entity
│   │   ├── repository
│   │   │   └── UserRepository.java      # MongoDB repository interface
│   │   ├── service
│   │   │   └── UserService.java         # Business logic layer
│   │   ├── controller
│   │   │   ├── ApiController.java       # REST API endpoints
│   │   │   ├── UserController.java      # Thymeleaf UI endpoints
│   │   │   └── AuthController.java      # JWT login endpoint
│   │   ├── security
│   │   │   ├── JwtUtil.java             # JWT creation and validation
│   │   │   ├── JwtAuthenticationFilter.java # JWT filter
│   │   │   ├── SecurityConfig.java      # Spring Security configuration
│   │   │   └── CustomUserDetailsService.java # User details service
│   ├── resources
│   │   ├── application.properties       # Configuration (e.g., MongoDB URI)
│   │   ├── templates                    # Thymeleaf templates
│   │   │   ├── index.html               # User list
│   │   │   ├── user-form.html           # User creation form
│   │   │   └── user-details.html        # User detail view
└── tests                              # Unit and integration tests
```

---

## **Endpoints**
### **Public Endpoints**
1. **Login**: `POST /api/auth/login`
    - Request: `{ "username": "user", "password": "password" }`
    - Response: `{ "token": "JWT_TOKEN" }`

### **Protected Endpoints**
1. **List Users**: `GET /api/users`
    - Requires JWT in `Authorization` header.

2. **Create User**: `POST /api/users`
    - Requires JWT in `Authorization` header.
    - Body: `{ "name": "John Doe", "email": "john@example.com" }`

3. **View User Details**: `GET /api/users/{id}`

---

## **Future Enhancements**
1. Integration with **OpenShift** or other cloud environments.
2. Role-based access control with more granular permissions.
3. CI/CD pipeline automation for deployment.

---

Feel free to reach out for further details or contributions! 😊
