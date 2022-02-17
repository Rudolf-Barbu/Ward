# Base image with maven installed already
FROM maven:3.8.4-eclipse-temurin-17 as builder

# Copy whole project inside docker
COPY . .

# Build project
RUN mvn clean package


# Base image containing OpenJDK 8, maintained by RedHat
FROM eclipse-temurin:17-jre-centos7

# Copy jar and pom from builder image to working directory
COPY --from=builder target/*.jar /ward.jar
COPY --from=builder pom.xml /pom.xml

EXPOSE 4000

# Run jar as sudo user on entry point
ENTRYPOINT java -jar ward.jar