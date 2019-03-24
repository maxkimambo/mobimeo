FROM openjdk:13-jdk-alpine

WORKDIR /app

EXPOSE 8081

# The application's jar file
ARG JAR_FILE=target/mobimeo-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} /app/mobimeo-0.0.1-SNAPSHOT.jar
COPY ./data /app/data

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/mobimeo-0.0.1-SNAPSHOT.jar"]