
FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} taskmanagement.jar

ENTRYPOINT ["java", "-jar", "/taskmanagement.jar"]

EXPOSE 8080