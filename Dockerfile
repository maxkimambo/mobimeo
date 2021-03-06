FROM openjdk:13-jdk-alpine

WORKDIR /app

EXPOSE 8081

ARG JAR_FILE=target/mobimeo-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} /app/mobimeo-0.0.1-SNAPSHOT.jar
COPY ./data /app/data

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/mobimeo-0.0.1-SNAPSHOT.jar"]