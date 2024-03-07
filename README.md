# Todo Backend Application

This repository contains the backend application for the Todo app.

## Prerequisites

Before running the application, ensure you have the following installed on your system:

- [Quarkus](https://quarkus.io)
- [Maven](https://maven.apache.org)
- [PostgreSQL](https://www.postgresql.org)

## Getting Started

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/MarVer003/todo.git
    ```

2. Navigate to the project directory:

    ```bash
    cd todo
    ```
    
4. Build and run the Quarkus application:

    ```bash
    ./mvnw clean package
    ./mvnw quarkus:dev
    ```

    The application will be accessible at [http://localhost:8080](http://localhost:8080).

## Testing the API

You can use commands like ```Invoke-WebRequest``` or ```Invoke-RestMethod``` (for better looking output) to interact with the API. Here are some example API endpoints:

- **List all tasks:**

    ```bash
    Invoke-RestMethod -Uri http://localhost:8080/tasks -Method GET
    ```

- **List a task:**

    ```bash
    Invoke-RestMethod -Uri http://localhost:8080/tasks/{UUID} -Method GET
    ```

- **Create a new task:**

    ```bash
    Invoke-RestMethod -Uri http://localhost:8080/tasks -Method POST -Headers @{"Content-Type"="application/json"} -Body '{"title":"Chores", "description":"vacuum the floor"}'
    ```

- **Update a task:**

    ```bash
    Invoke-RestMethod -Uri http://localhost:8080/tasks/{UUID} -Method PUT -Headers @{"Content-Type"="application/json"} -Body '{"completed":true}'
    ```

- **Delete a task:**

    ```bash
    Invoke-RestMethod -Uri http://localhost:8080/tasks/{UUID} -Method DELETE
    ```

- **Delete all tasks:**

    ```bash
    Invoke-RestMethod -Uri http://localhost:8080/tasks -Method DELETE
    ```

## Swagger Documentation

Additionally you can also access swagger documentation at [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui).


## Stopping the Application

To stop the application press ```q```
