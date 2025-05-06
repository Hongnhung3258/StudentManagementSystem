# Architecture Overview - University Management System

## 1. Overview

The University Management System is a web-based application designed to facilitate the administration of university resources including students, lecturers, departments, courses, and classes. The system provides functionality for managing academic information, news, and operational data within a university context.

The application follows a standard Spring Boot architecture with a clean separation between presentation, business logic, and data access layers. It uses Java 17 and is built on the Spring ecosystem, leveraging Spring MVC for web functionality, Spring Data JPA for database operations, and Spring Security for authentication and authorization.

## 2. System Architecture

### 2.1 Overall Architecture

The system follows a 3-tier architecture:

1. **Presentation Layer**: Thymeleaf templates for server-side rendering of HTML views
2. **Application Layer**: Spring MVC controllers and service layer for business logic
3. **Data Access Layer**: Spring Data JPA repositories for database interactions

### 2.2 Backend Architecture

The backend is built on Spring Boot 3.2.3 and follows these architectural patterns:

- **MVC Pattern**: Controllers handle HTTP requests, delegate to services, and return views
- **Repository Pattern**: Interfaces that extend JpaRepository provide data access methods
- **Service Layer**: Business logic is encapsulated in service interfaces and implementations
- **Dependency Injection**: Spring's IoC container manages component dependencies

### 2.3 Frontend Architecture

The frontend uses:

- **Thymeleaf**: Server-side templating engine for dynamic HTML generation
- **Bootstrap 5**: CSS framework for responsive design
- **Feather Icons**: Icon library for UI elements
- **JavaScript**: Basic client-side interactivity

### 2.4 Database Architecture

The application uses:

- **MySQL**: Relational database for persistent storage
- **JPA/Hibernate**: ORM for database operations and entity management
- **Schema Design**: Normalized database schema with appropriate relationships between entities

## 3. Key Components

### 3.1 Entity Layer

Core domain objects represented as JPA entities:

- `Student`: Represents student information including personal details and department
- `Lecturer`: Represents faculty members with academic qualifications and department
- `Department`: Represents academic departments with codes and head lecturers
- `Course`: Represents academic subjects with credits and associated departments
- `Class`: Represents course instances with assigned lecturers and schedules
- `News`: Contains university announcements and notifications
- `Login`: Stores authentication credentials

### 3.2 Repository Layer

JPA repositories provide data access methods:

- `StudentRepository`: CRUD operations for student data
- `LecturerRepository`: CRUD operations for lecturer data
- `DepartmentRepository`: CRUD operations for department data
- `CourseRepository`: CRUD operations for course data
- `ClassRepository`: CRUD operations for class data
- `NewsRepository`: CRUD operations for news content
- `LoginRepository`: Operations for authentication data

### 3.3 Service Layer

Services implement business logic:

- `StudentService`: Student management operations
- `LecturerService`: Lecturer management operations
- `DepartmentService`: Department management operations
- `CourseService`: Course management operations
- `ClassService`: Class scheduling and management
- `NewsService`: News publication and management
- `LoginService`: Authentication operations
- `DashboardService`: Statistics and summary information

### 3.4 Controller Layer

Controllers handle HTTP requests:

- `StudentController`: Student CRUD operations
- `LecturerController`: Lecturer CRUD operations
- `DepartmentController`: Department CRUD operations
- `CourseController`: Course CRUD operations
- `ClassController`: Class CRUD operations
- `NewsController`: News CRUD operations
- `AuthController`: Authentication and session management
- `DashboardController`: Summary dashboard display

### 3.5 View Layer

Thymeleaf templates organized by domain area:

- `/templates/student/`: Student management views
- `/templates/lecturer/`: Lecturer management views
- `/templates/department/`: Department management views
- `/templates/course/`: Course management views
- `/templates/class/`: Class management views
- `/templates/news/`: News management views
- `/templates/fragments/`: Reusable UI components

### 3.6 Utility Components

Helper classes and utilities:

- `CodeGenerator`: Generates unique codes for entities
- `PasswordGenerator`: Creates secure random passwords

## 4. Data Flow

### 4.1 Request Handling Flow

1. HTTP request arrives at a controller
2. Controller validates input and delegates to appropriate service
3. Service executes business logic, typically involving repository calls
4. Repository interacts with database via JPA/Hibernate
5. Service processes results
6. Controller prepares model and selects view
7. Thymeleaf renders HTML with model data
8. Response is sent to the client

### 4.2 Authentication Flow

1. User submits credentials via login form
2. `AuthController` processes login request
3. `LoginService` authenticates credentials
4. Upon successful authentication, session is created
5. User is redirected to dashboard
6. Subsequent requests include session cookie for authentication

## 5. External Dependencies

### 5.1 Core Dependencies

- `spring-boot-starter-data-jpa`: Database operations via JPA
- `spring-boot-starter-security`: Authentication and authorization
- `spring-boot-starter-thymeleaf`: Server-side templating
- `spring-boot-starter-validation`: Input validation
- `spring-boot-starter-web`: Web MVC functionality

### 5.2 Frontend Dependencies (CDN-hosted)

- Bootstrap 5: CSS framework
- Feather Icons: Icon library

### 5.3 Development Tools

- Spring Boot DevTools: Development time productivity tools
- Maven: Dependency management and build tool

## 6. Deployment Strategy

### 6.1 Build Process

- Maven is used for building the application
- The application is packaged as a JAR file
- Java 17 is required for running the application

### 6.2 Runtime Configuration

- Application is configured via `application.properties`
- Database configuration includes connection details and JPA settings
- Server is configured to run on port 8000 by default

### 6.3 Deployment Approach

- The application can be run via Spring Boot's embedded Tomcat server
- Port 5000 is used for deployed applications (configured in `.replit`)
- The application is designed to be containerizable

### 6.4 Scaling Considerations

- Session state is stored in-memory (non-distributed)
- Database connections are pooled for efficiency
- Static assets are cached via Thymeleaf's caching mechanism
- Logging is configured for different levels based on environment

## 7. Security Architecture

### 7.1 Authentication

- Form-based authentication via Spring Security
- Credentials stored in the `login` table
- Session-based authentication with 30-minute timeout

### 7.2 Authorization

- Role-based access control (details not fully implemented in current code)
- URL-based security constraints

### 7.3 Security Mechanisms

- CSRF protection disabled in development (should be enabled in production)
- Password encoding with BCrypt
- Form validation to prevent malicious input