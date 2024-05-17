# Use a multi-stage build process. First, use a Maven image to build the project.

# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 as builder

# Copy the project files to the container
COPY . /app

# Set the working directory
WORKDIR /app

# Use Maven to build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the final Docker image using Amazon Corretto
FROM amazoncorretto:17

# Set the working directory in the Docker image
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/employees-credit-0.0.1-SNAPSHOT.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]



