# Use the official OpenJDK 17 image as the base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the env.properties file into the container
COPY env.properties /app

# Copy the Maven executable JAR file into the container
COPY target/ifepill-customer-service.jar /app

# Expose the port your application runs on
EXPOSE 8081

# Command to run the application when the container starts
CMD ["java", "-jar", "lifepill-customer-service.jar"]
