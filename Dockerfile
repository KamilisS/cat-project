FROM openjdk:17-jdk-slim

WORKDIR /app

COPY /target/*.jar cat-project.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "cat-project.jar"]