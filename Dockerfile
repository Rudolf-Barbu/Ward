# Base image with maven installed already
FROM maven:3.6.3-jdk-8 as builder

# Copy whole project inside docker
COPY . .

# Build project
RUN mvn clean package


# Base image containing OpenJDK 8, maintained by RedHat
FROM openjdk:8-jre-alpine

# Update apt repo and install sudo package
RUN apk update && \
    apk add --no-cache dmidecode eudev && \
    rm -rf /var/cache/apk/*

# Copy jar and pom from builder image to working directory
COPY --from=builder target/*.jar /ward.jar
COPY --from=builder pom.xml /pom.xml

EXPOSE 4000

# Run jar as sudo user on entry point
ENTRYPOINT java -jar ward.jar
