FROM openjdk:21-jdk-slim
LABEL authors="maksimignatov"

WORKDIR /app
COPY    target/TaskManagementSystem-0.0.1-SNAPSHOT.jar /app/TaskManagementSystem-0.0.1-SNAPSHOT.jar
EXPOSE 808/tcp
CMD ["java", "-XX:+UseG1GC", "-jar", "TaskManagementSystem-0.0.1-SNAPSHOT.jar"]
