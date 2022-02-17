# Base image with maven installed already
FROM maven:eclipse-temurin as builder

# Copy whole project inside docker
COPY . .

# Build project
RUN mvn clean package


# Base image containing OpenJDK 17
FROM eclipse-temurin:17-jre

# Copy jar and pom from builder image to working directory
COPY --from=builder target/*.jar /ward.jar
COPY --from=builder pom.xml /pom.xml

EXPOSE 4000

# Run jar as sudo user on entry point
ENTRYPOINT java -jar ward.jar