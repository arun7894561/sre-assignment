FROM openjdk:17

WORKDIR /app

COPY target/user-metadata-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]