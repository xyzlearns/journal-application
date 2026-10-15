📔 Journal App

A backend application for managing personal journal entries, built with Spring Boot and MongoDB. The project demonstrates REST API development, database integration, and backend security using the Spring ecosystem.

🚀 Features

- Journal Management — Create, retrieve, update, and delete journal entries.
- RESTful APIs — HTTP endpoints for interacting with journal data.
- MongoDB Integration — Store journal entries in MongoDB Atlas.
- Spring Security — Security infrastructure for protecting application resources.
- Maven — Dependency management and project build automation.
- Environment-Based Configuration — Keep database credentials outside the source code.

🛠️ Tech Stack

Technology| Purpose
Java 8| Programming language
Spring Boot 2.7.16| Backend framework
Spring Web| REST API development
Spring Data MongoDB| Database operations
MongoDB Atlas| Cloud database
Spring Security| Application security
Lombok| Boilerplate code reduction
Maven| Build and dependency management

📂 Project Structure

journal-application/
└── journalApp-backened/
    ├── pom.xml
    └── src/
        └── main/
            ├── java/
            │   └── net/
            │       └── engineeringdigest/
            │           └── journalApp/
            │               ├── controller/
            │               │   └── JournalEntryController.java
            │               ├── entity/
            │               │   └── JournalEntry.java
            │               ├── repository/
            │               │   └── JournalEntryRepository.java
            │               └── service/
            │                   └── JournalEntryServices.java
            └── resources/
                └── application.properties

The structure above represents the core components; additional classes may exist in the repository.

⚙️ Getting Started

Prerequisites

Ensure you have the following installed:

- Java Development Kit (JDK) 8
- Apache Maven
- A MongoDB Atlas account and database
- Git

1. Clone the Repository

git clone <YOUR_GITHUB_REPOSITORY_URL>
cd journal-application/journalApp-backened

Replace the placeholder with your actual GitHub repository URL.

2. Configure MongoDB Atlas

Create a MongoDB Atlas cluster, configure a database user, and allow your development IP address to connect.

Set the MongoDB connection string as an environment variable:

export MONGODB_URI="mongodb+srv://<username>:<password>@<cluster-url>/<database-name>?retryWrites=true&w=majority"

Never commit database credentials or connection strings containing passwords to GitHub.

Configure "src/main/resources/application.properties" to read the environment variable:

spring.application.name=journal-application
spring.data.mongodb.uri=${MONGODB_URI}

Preserve any additional configuration required by your application.

3. Build the Project

mvn clean compile

4. Run the Application

mvn spring-boot:run

Alternatively, package and run the application:

mvn clean package
java -jar target/journalApp-*.jar

The application will typically start at:

http://localhost:8080

🔌 API Overview

The application exposes journal-related REST endpoints under "/journals".

Method| Endpoint| Purpose
GET| "/journals"| Retrieve journal entries
POST| "/journals"| Create a journal entry

The exact request bodies, response formats, and additional CRUD endpoints depend on the current controller implementation.

Example Request

GET /journals
Host: localhost:8080
Accept: application/json

Use Postman, IntelliJ HTTP Client, or "curl" to test the endpoints.

🔐 Security

Spring Security is included in the project.

- Keep database credentials in environment variables.
- Do not expose sensitive configuration in version control.
- Configure authentication and authorization according to the application's requirements.
- Use HTTPS and appropriate access controls when deploying publicly.

Note: Encryption and end-to-end encryption (E2EE) should only be claimed once the corresponding encryption mechanisms have been implemented and verified.

🧪 Testing

Run the project's automated tests with Maven:

mvn test

🗺️ Roadmap

- [ ] Complete journal CRUD operations.
- [ ] Implement user registration and authentication.
- [ ] Add user-specific journal access controls.
- [ ] Strengthen API validation and exception handling.
- [ ] Add automated unit and integration tests.
- [ ] Deploy the backend to a cloud hosting platform.

🤝 Contributing

Contributions, suggestions, and bug reports are welcome.

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Open a pull request describing your changes.

📄 License

No license has been specified yet. Add a "LICENSE" file before distributing the project under an open-source license.

---

Built with Java, Spring Boot, and MongoDB.
