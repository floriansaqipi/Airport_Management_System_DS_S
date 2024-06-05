# Airport Management System

## Overview

Our project is an Airport Management System designed as a REST API, developed by a dedicated team of five as part of our Distributed Systems course. Utilizing the robust Spring Boot Java framework, this API is tailored for effective airport operations and management, offering a comprehensive solution for handling airport data efficiently.

## Technical Aspects

### Framework and Database

- **Spring Boot**: Chosen for its extensive support and simplicity in developing Spring applications, Spring Boot has enabled us to build a high-performing REST API. Its auto-configuration and embedded server support significantly reduced development time and complexity.
- **MySQL**: As our database management system, MySQL allowed us to handle complex data structures efficiently. We implemented around 20 tables to accommodate the extensive data needs of an airport management system.

## Database Tables

- **Airports**
- **Flights**
- **Aircrafts**
- **Airlines**
- **Passengers**
- **Tickets**
- **Flight_Schedule**
- **Employees**
- **Baggage**
- **Boarding_Passes**
- **Check-In**
- **Gate_Assignments**
- **Security_Checkpoints**
- **Flight_Crews**
- **Maintenance**
- **Cargo**
- **Airport_Services**
- **Parking**
- **Rental_Services**
- **Feedback**


![ER-DIAGRAM](https://github.com/floriansaqipi/Airport_Management_System_DS_S/assets/53408143/d9379a89-0df6-4160-a556-82a72349f39d)

### Database Migration with Liquibase

To manage database versions and ensure consistency across different development environments, we incorporated **Liquibase**. This tool has been instrumental in automating database migrations, allowing for smooth transitions between versions and sharing of the database structure (DDL) through scripts located in `/resources/db/changelog/scripts`. This approach guarantees that our database schema is version-controlled and easily accessible to all project contributors.

Below is a list of the primary database tables utilized in our Airport Management System. Each table is crucial for storing the specific types of information required for comprehensive airport management.

### Implementation Details

- **Entities and JPA**: By modeling database tables with Entities and utilizing JPA annotations(@OneToOne, @ManyToOne, @OneToMany and various others) we defined the intricate relationships between them. This structure facilitated the mapping of our relational database schema to the object model inherent in our application. Initially, we experimented with a DAO approach where we directly used the entity manager and designed the CRUD operations on our own. However, we found that utilizing the repositories provided by Spring Data JPA offered a more viable and efficient solution, reducing boilerplate code and enhancing maintainability.
- **Repository Layer**: Leveraging JPA Repositories provided by the JPA Data API, we created repositories for each entity using the `JpaRepository` interface. This setup enabled us to perform CRUD operations seamlessly. The `JpaRepository` interface offers a wide range of methods for manipulating the database based on the type of the entity and the type of the primary key ID, right out of the box. This feature significantly reduced the amount of repetitive code and simplified database interaction, making it a decent choice for our project.
- **Service Layer**: Through dependency injection, various repositories were integrated into the service layer. This design pattern allows our controller layer to interact with data sources in a streamlined manner, courtesy of Spring Data JPA ORM.
- **REST Controllers**: Utilizing Spring Web starter dependencies, we established a RestController for each entity. These controllers expose endpoints for POST, PUT, DELETE, and GET methods, facilitating the management of airport data. These controllers are responsible for handling the requests sent by the client, ensuring seamless communication between the client and the server for CRUD operations on airport data.

---

### Authentication and Authorization

To secure our API, we implemented custom authentication and authorization mechanisms:

- **Custom Spring Filter**: We developed a custom Spring filter to handle authentication. This filter intercepts incoming requests and checks for valid authentication tokens.
- **Custom User Details Provider**: We created a custom user details provider to load user-specific data. This setup allows us to control which routes are public and which are private.
- **Role-Based Authorization**: For private routes, we implemented role-based authorization. This ensures that users can only access resources and perform actions that are permitted by their roles and abilities. Different roles such as Admin, Staff, and Passenger have varying levels of access and permissions.

### Documentation

To document our REST API, we utilized **Swagger**. Swagger provides an interactive interface for exploring and testing the API endpoints, making it easier for developers to understand and use our API. The Swagger documentation is automatically generated from the annotations in our controllers and can be accessed.

### Exception Handling

Our API incorporates a General Exception Handler, ensuring that any errors are communicated effectively through well-structured error messages. This feature enhances the robustness and user-friendliness of our system.


### Additional Tools Used

- **Postman**: Applied for applying CRUD operations on our APIs, encompassing GET, POST, PUT and DELETE requests. Postman streamlined the process of validating API endpoints, ensuring the functionality and reliability of our application's API layer.

---

## Requirements & Running the Application

Here are the dependencies that we used

- org.springframework.boot:spring-boot-starter-data-jpa
- org.springframework.boot:spring-boot-starter-web
- org.liquibase:liquibase-core
- com.mysql:mysql-connector-j (runtime scope)
- org.springframework.boot:spring-boot-starter-test (test scope)

Here are short descriptions for each dependency:

- **Spring Boot Starter Data JPA**: Provides support for Spring Data JPA which simplifies the implementation of data access layers.

- **Spring Boot Starter Web**: Enables building web applications using Spring MVC and embeds a Tomcat server for running the application.

- **Liquibase Core**: Offers database schema version control and automated deployment functionalities, ensuring smooth database migrations.

- **MySQL Connector/J**: Provides connectivity to MySQL databases from Java applications, facilitating data retrieval and manipulation.

- **Spring Boot Starter Test**: Includes dependencies required for testing Spring Boot applications, such as JUnit, Spring Test, and other testing utilities.


To utilize our Airport Management System REST API, the following are required:
- **Java JDK**: Ensure that Java JDK is installed on your system.
- **IDE or Command Line**: The application can be run on any Java-supporting IDE, such as IntelliJ, or directly from the command line by generating a `.jar` file.


Once the application is running, access the API by navigating to `localhost:8080/api/*endpoint*` using a client tool or a web browser. The embedded Tomcat server will automatically start upon running the `.jar` file, serving the API endpoints.

## Contributors

1. [Ardi Zariqi](https://github.com/ArdiZariqi)
2. [Ardit Gjyrevci](https://github.com/ArditGjyrevci)
3. [Arianit Gashi](https://github.com/ArianitSGashi)
4. [Florian Saqipi](https://github.com/floriansaqipi)
5. [Gentrit Kryeziu](https://github.com/Gentrit851)

## Acknowledgments

We extend our deepest gratitude to our professors and mentors in the Distributed Systems course. Their guidance and support have been invaluable throughout this project. Additionally, we thank the Spring and MySQL communities for providing the tools and resources that made this project possible.

---

This README aims to provide a comprehensive guide to the Airport Management System REST API. For further information or assistance, please refer to the documentation or contact the contributors.

 
