# Deploying the LifePill Customer Service

This document provides step-by-step instructions on how to build and deploy the `lifepill-customer-service` application using Maven and Docker.

## Prerequisites

Ensure that you have the following tools installed:
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/)

## Build and Package the Application

1. **Navigate to the project directory**:
   Open a terminal and change the directory to the root of your project:

   ```bash
   cd /path/to/your/project
   ```

2. **Clean and build the project**:
   Run the following Maven command to clean the project and install dependencies, skipping the tests:

   ```bash
   mvn clean install -DskipTests
   ```

   This will generate the `lifepill-customer-service.jar` inside the `target/` directory.

## Build the Docker Image

1. **Create a Docker image**:
   After the JAR file is generated, build the Docker image using the following command:

   ```bash
   docker build -t lifepill-customer-service .
   ```

   This will use the Dockerfile in your project directory to create a Docker image.

2. **Verify the image**:
   To check if the image was built successfully, run:

   ```bash
   docker images
   ```

   You should see `lifepill-customer-service` listed in the output.

## Run the Docker Container

1. **Run the container**:
   Start the Docker container using the following command:

   ```bash
   docker run -d -p 8076:8076 lifepill-customer-service
   ```

   This command will:
    - Run the container in detached mode (`-d`).
    - Map port `8076` from the container to port `8076` on your host machine.

2. **Check running containers**:
   To verify that the container is running, use the following command:

   ```bash
   docker ps
   ```

   You should see the `lifepill-customer-service` container listed as running.

## Access the Application

Once the container is running, you can access the application by navigating to:

```
http://localhost:8076
```

## Stopping the Docker Container

To stop the running Docker container, you can use:

```bash
docker stop <container_id>
```

To get the `<container_id>`, run `docker ps` and copy the ID of the running `lifepill-customer-service` container.

---

This file provides a structured guide for deploying the `lifepill-customer-service`, covering everything from building the project to running it inside a Docker container.