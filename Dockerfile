# Base image containing OpenJDK 8, maintained by RedHat
FROM adoptopenjdk/openjdk8

# Set name of user
ARG USER=user

# Update apt repo and install sudo package
RUN apt-get update \
 && apt-get install -y sudo

# Add the user with no password
RUN adduser --disabled-password --gecos '' $USER
# Add user with elevated permission
RUN adduser $USER sudo
RUN echo '%sudo ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers.d/$USER

# Switch user
USER $USER

# Symlink to java executable to make it avaiable to sudo user
RUN sudo ln -s $JAVA_HOME/bin/java /usr/bin/java

# Copy jar from target to working directory
COPY target/*.jar /ward.jar

# Run jar as sudo user on entrypoint
ENTRYPOINT sudo  java -jar ward.jar