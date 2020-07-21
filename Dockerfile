# Base image with maven installed already
FROM maven:3.6.3-jdk-8 as builder

# Copy whole project inside docker
COPY . .

# Build project
RUN mvn clean package


# Base image containing OpenJDK 8, maintained by RedHat
FROM adoptopenjdk:8-jre-hotspot

# Set name of user
ARG USER=user

# Update apt repo and install sudo package
RUN apt-get update && \
    apt-get install -y sudo && \
    apt-get install -yq dmidecode && \
    rm -rf /var/lib/apt/lists/*

# Add the user with no password
RUN adduser --disabled-password --gecos '' $USER

# Add user with elevated permission
RUN adduser $USER sudo
RUN echo '%sudo ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers.d/$USER

# Switch user
USER $USER

# Symlink to java executable to make it available to sudo user
RUN sudo ln -s $JAVA_HOME/bin/java /usr/bin/java

# Copy jar and pom from builder image to working directory
COPY --from=builder target/*.jar /ward.jar
COPY --from=builder pom.xml /pom.xml

EXPOSE 4000

# Run jar as sudo user on entry point
ENTRYPOINT sudo java -jar ward.jar
