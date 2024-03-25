# Stage 1: Build the Spring Boot application
FROM maven:3-openjdk-17-slim AS BUILD-stage

# Set the working directory
WORKDIR /var/app/server

# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy the entire project and build the WAR file
COPY . .
RUN mvn package -D skipTests

# Stage 2: Create the final image with the built WAR file
FROM maven:3-openjdk-17-slim AS DEPLOY-stage

# Set working directory
WORKDIR /var/app/server

# Copy the built WAR file from the previous stage
COPY --from=builder /var/app/server/target/server*.jar /var/app/server/server*.jar

# Expose port 8080 (the default port for Tomcat)
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "/var/app/server/server*.jar"]