FROM openjdk:21-jdk-slim
LABEL authors="maksimignatov"

WORKDIR /app
COPY task-management-system-web/target/task-management-system-web-0.0.1-SNAPSHOT.jar /app/task-management-system-web-0.0.1-SNAPSHOT.jar
EXPOSE 808/tcp
CMD ["java", "-XX:+UseG1GC", "-jar", "task-management-system-web-0.0.1-SNAPSHOT.jar"]
