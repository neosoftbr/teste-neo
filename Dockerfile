FROM maven:3.6.3-jdk-11-slim AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package

FROM openjdk:11-jdk-slim

COPY --from=MAVEN_BUILD /target/neo.user.jar /app.jar

EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]