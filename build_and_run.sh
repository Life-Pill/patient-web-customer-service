#!/bin/bash

mvn clean install -DskipTests
sudo chmod 666 /var/run/docker.sock

# Build the Docker image
docker build -t lifepill-customer-service:latest .

# Stop and remove any existing container with the same name
docker stop lifepill-customer-service || true
docker rm lifepill-customer-service || true

# Run the Docker container
docker run -d -p 8076:8076 --name lifepill-customer-service lifepill-customer-service:latest

echo "Container lifepill-customer-service is running and accessible on port 8076"