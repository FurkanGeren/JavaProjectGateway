

FROM openjdk:8-jdk-alpine
ADD target/usermanagement-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]