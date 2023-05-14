# Architecture
<pre>
   +---------------------------------------------------------+
   |                     Client Application                  |
   +---------------------------------------------------------+
                               |
                               | Sends HTTP requests to API endpoints
                               |
   +---------------------------------------------------------+
   |                   API Controllers/Endpoints             |
   +---------------------------------------------------------+
                               |
                               | Handles incoming HTTP requests
                               |
   +---------------------------------------------------------+
   |                        Security                         |
   +---------------------------------------------------------+
                               |
                               | Authenticates and authorizes requests
                               |
   +---------------------------------------------------------+
   |                    Service Layer                        |
   +---------------------------------------------------------+
                               |
                               | Implements business logic and data manipulation
                               |
   +---------------------------------------------------------+
   |                      Caching                            |
   +---------------------------------------------------------+
                               |
                               | Stores frequently accessed data in cache
                               |
   +---------------------------------------------------------+
   |                  Data Access Layer (Repositories)       |
   +---------------------------------------------------------+
                               |
                               | Interacts with the PostgreSQL database using Spring Data JPA
                               |
   +---------------------------------------------------------+
   |                      PostgreSQL Database                |
   +---------------------------------------------------------+
                               |
                               | Stores and retrieves data using SQL queries
                               |
   +---------------------------------------------------------+

</pre>


In this schema, the client application sends HTTP requests to API endpoints defined in the API Controllers/Endpoints. The API Controllers handle these requests and invoke the appropriate methods in the Service Layer. The Service Layer contains the business logic and performs data manipulation as needed. It interacts with the Data Access Layer, which includes repositories implemented using Spring Data JPA. The Data Access Layer communicates with the PostgreSQL database, storing and retrieving data using SQL queries.

The Security component is added to authenticate and authorize requests, ensuring proper access control to the API endpoints. The Caching component stores frequently accessed data in cache, which helps improve performance and reduce database load. The Messaging component allows the system to send and receive messages asynchronously, facilitating communication between different parts of the application or with external systems.


## Libraries:

- Spring Boot Starter Web: This library includes everything you need to build a web application with Spring Boot, including embedded web server support, Spring MVC, and related components.

- Spring Boot Starter Data JPA: This library provides support for Java Persistence API (JPA) to interact with relational databases. It includes Spring Data JPA, Hibernate, and related dependencies.

- PostgreSQL Driver: You will need the PostgreSQL JDBC driver to connect your Spring Boot application with the PostgreSQL database. The driver allows your application to communicate with the database and execute SQL queries.

- Spring Boot Starter Test: This library includes testing dependencies for writing unit tests and integration tests for your Spring Boot application.

- JSON Web Token (JWT) Library: If you want to implement JWT-based authentication in your application, you will need a JWT library like jjwt. ()

## Standard in use:

 - JSON Web Token Authentication

<pre>
   +---------------------------------------------------------+
   |                    Client Application                   |
   +---------------------------------------------------------+
                               |
                               | Sends login request with credentials
                               |
   +---------------------------------------------------------+
   |                    Authentication Controller             |
   +---------------------------------------------------------+
                               |
                               | Authenticates user credentials
                               |
   +---------------------------------------------------------+
   |                  UserDetailsService Implementation        |
   +---------------------------------------------------------+
                               |
                               | Retrieves user details from the database
                               |
   +---------------------------------------------------------+
   |                  Security Configuration                   |
   +---------------------------------------------------------+
                               |
                               | Configures authentication manager, JWT filter, and endpoints
                               |
   +---------------------------------------------------------+
   |                     JwtTokenProvider                    |
   +---------------------------------------------------------+
                               |
                               | Generates and validates JWT tokens
                               |
   +---------------------------------------------------------+
   |                  API Endpoints/Controllers              |
   +---------------------------------------------------------+
                               |
                               | Secured API endpoints
                               |
   +---------------------------------------------------------+
   |                        Database                         |
   +---------------------------------------------------------+
</pre>

In this schema, the client application sends a login request with user credentials. The Authentication Controller authenticates the user credentials and retrieves user details from the UserDetailsService implementation, which interacts with the database using Spring Data JPA. The Security Configuration class configures the authentication manager, JWT filter, and the secured API endpoints. The JwtTokenProvider generates and validates JWT tokens. The API endpoints/controllers handle the secured API requests. The database stores the user information.

Please note that this is a simplified schema to illustrate the major components involved. The actual implementation may involve additional classes, configurations, and interactions between components.



# Diagram illustrating the flow of JWT authentication

<pre>
   +---------------------------------------------------------+
   |                     Client Application                   |
   +---------------------------------------------------------+
           |                                                   
           |              1. Login Request (with credentials)   
           |                                                   
           v                                                   
   +---------------------------------------------------------+
   |                   Authentication Server                  |
   +---------------------------------------------------------+
           |                                                   
           |          2. Authenticate User Credentials          
           |                                                   
           v                                                   
   +---------------------------------------------------------+
   |                       User Repository                    |
   +---------------------------------------------------------+
           |                                                   
           |               3. Retrieve User Details             
           |                                                   
           v                                                   
   +---------------------------------------------------------+
   |                 Generate JWT and Response                 |
   +---------------------------------------------------------+
           |                                                   
           |            4. Generate JWT with User Details       
           |                                                   
           v                                                   
   +---------------------------------------------------------+
   |                     Client Application                   |
   +---------------------------------------------------------+
           |                                                   
           |            5. Receive JWT and store locally        
           |                                                   
           v                                                   
   +---------------------------------------------------------+
   |                  Subsequent API Requests                 |
   +---------------------------------------------------------+
           |                                                   
           |                6. Include JWT in Header            
           |                  (Authorization: Bearer <token>)   
           v                                                   
   +---------------------------------------------------------+
   |                   Authentication Server                  |
   +---------------------------------------------------------+
           |                                                   
           |          7. Validate JWT and authorize access      
           |                                                   
           v                                                   
   +---------------------------------------------------------+
   |                  Process Request and Respond              |
   +---------------------------------------------------------+
           |                                                   
           |                8. Process Request and Respond      
           |                                                   
           v                                                   
   +---------------------------------------------------------+
   |                     Client Application                   |
   +---------------------------------------------------------+
           |                                                   
           |                    9. Receive Response             
           |                                                   
           v                                                   
</pre>

In this diagram:

  - The client application sends a login request with credentials to the authentication server.
  - The authentication server verifies and authenticates the user's credentials.
  - The authentication server retrieves the user's details from the user repository.
  - The authentication server generates a JWT (JSON Web Token) containing the user's details.
  - The client application receives the JWT and stores it locally (e.g., in local storage or memory).
  - For subsequent API requests, the client application includes the JWT in the request header as "Authorization: Bearer <token>".
  - The authentication server validates the JWT to ensure its authenticity and authorizes access based on the user's role or permissions.
  - The authentication server processes the API request and generates a response.
  - The client application receives the response from the authentication server.

Please note that this is a simplified flow, and the actual implementation may involve additional steps or variations based on your specific requirements and application architecture.