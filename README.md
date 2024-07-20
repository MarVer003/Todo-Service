# Todo Service

This repository contains a simple **Todo Service** that can Create, Read, Update and Delete (CRUD) tasks to/from postgres database.

## Prerequisites

Before running the service, ensure you have the following installed on your system:

- [Java](https://www.oracle.com/java/technologies/downloads/#java21)
- [Intellij IDEA](https://www.jetbrains.com/idea/download/) (or any other IDE)
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Postman](https://www.postman.com/downloads/) (Optional)

## Getting Started

1. Clone this repository to your local machine:

    ```powershell
    git clone https://github.com/MarVer003/Todo-Service.git
    ```

2. Run docker:

    ```powershell
    docker run --name todoService -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=todo -p 5432:5432 -d postgres
    ```
    
4. Navigate to the project directory and run the service:

    ```powershell
    ./mvnw compile quarkus:dev
    ```

    The application will be accessible at [http://localhost:8080](http://localhost:8080).

## Testing the API

You can use commands like `Invoke-WebRequest` or `Invoke-RestMethod` (for better looking output) to interact with the API. Alternatively you can also use **Postman**. Here are some example API endpoints:

- **List all tasks:**

    ```powershell
    Invoke-RestMethod -Uri http://localhost:8080/tasks -Method GET
    ```

- **List a task:**

    ```powershell
    Invoke-RestMethod -Uri http://localhost:8080/tasks/{UUID} -Method GET
    ```

- **Create a new task:**

    ```powershell
    Invoke-RestMethod -Uri http://localhost:8080/tasks -Method POST -Headers @{"Content-Type"="application/json"} -Body '{"title":"Chores", "description":"vacuum the floor"}'
    ```

- **Update a task:**

    ```powershell
    Invoke-RestMethod -Uri http://localhost:8080/tasks/{UUID} -Method PUT -Headers @{"Content-Type"="application/json"} -Body '{"completed":true}'
    ```

- **Delete a task:**

    ```powershell
    Invoke-RestMethod -Uri http://localhost:8080/tasks/{UUID} -Method DELETE
    ```

- **Delete all tasks:**

    ```powershell
    Invoke-RestMethod -Uri http://localhost:8080/tasks -Method DELETE
    ```

## Swagger Documentation

Additionally you can also access swagger documentation at [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui) on running service.


## Stopping the Application

To stop the application press `q`
