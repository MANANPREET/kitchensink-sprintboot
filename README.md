
# **Kitchensink Application Migration**

This project is a migrated version of the `kitchensink` JBoss application to a modern Spring Boot 3.1.0 platform. It includes updates to work with a MongoDB database and implements role based security using Spring Security 6.0. The application also incorporates Thymeleaf for UI rendering and follows modern best practices for validations, exception handling, and unit testing.

---

## **Features**
1. **Modernized Backend**:
    - Migrated from JBoss to Spring Boot 3.1.0.
    - Java 21-based runtime.

2. **Database Migration**:
    - Replaced the relational database with MongoDB.

3. **Security**:
    - Implements user role based authentication and authorization using Spring Security 6.0.
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
3. Install and run a **MongoDB instance** (default URI: `mongodb://localhost:27017/MemberRegistration`).

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
    - **Thymeleaf UI**: [http://localhost:8080/member](http://localhost:8080/member)
    - **Login Endpoint**: [http://localhost:8080/login](http://localhost:8080/login)
    - **Register Endpoint**: [http://localhost:8080/register](http://localhost:8080/register)


---

## **Authentication and Authorization**
- **Login**:
 Register using above url and then try to login.

---

## **Directory Structure**
```
kitchensink-springboot
├── pom.xml                      # Maven dependencies
├── src/main
│   ├── java/com/example/kitchensink
│   │   ├── WebApplication.java  # Main application entry point
│   │   ├── model
│   │   │   └── User.java                # MongoDB User entity
│   │   ├── repository
│   │   │   └── UserRepository.java      # MongoDB repository interface
│   │   ├── service
│   │   │   └── UserService.java         # Business logic layer
│   │   ├── controller      # REST API endpoints
│   │   │   ├── MemberController.java    # Thymeleaf UI endpoints
│   │   │   └── AuthController.java      #  login endpoint
│   │   ├── security
│   │   │   ├── SecurityConfig.java      # Spring Security configuration
│   │   │   └── CustomUserDetailsService.java # User details service
│   ├── resources
│   │   ├── application.properties       # Configuration (e.g., MongoDB URI)
│   │   ├── templates                    # Thymeleaf templates
│   │   │   ├── index.html               # User list
│   │   │   ├── user-form.html           # User creation form
│   │   │   └── user-details.html        # User detail view
└── tests                              # Unit tests
```

---

## **Endpoints**
### **Public Endpoints**
1. **Login**: `GET /login`
2. **Register**: 
   1. `GET /register`
   2. `POST /register/save`
3. **Logout**: `GET /logout`

### **Protected Endpoints**
1. **List Members**: `GET /member`
    - Requires Login / Authenticated User

2. **Create Member**: `POST /members/new`
   - Requires Login / Authenticated User

3. **View and Edit User Details**: 
   1. `GET /members/{id}`
   2. `GET /members/{id}/edit`
4. **Delete User Details**:
   1. `GET/members/{id}/delete`

---

## **Future Enhancements**
1. Integration with **OpenShift** or other cloud environments. Or Deployment to Heruko.
2. Role-based access control with more granular permissions.
3. CI/CD pipeline automation for deployment.

---

Feel free to reach out for further details or contributions! 😊
