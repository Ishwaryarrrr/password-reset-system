# Build stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Run stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/password-reset-1.0-SNAPSHOT.jar app.jar
# Command to run the application (If this was a web app)
ENTRYPOINT ["java", "-jar", "app.jar"]