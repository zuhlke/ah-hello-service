
FROM openjdk:8-jdk-alpine
COPY build/libs/hello-service-0.0.1.jar .
EXPOSE 8080
CMD java -jar hello-service-0.0.1.jar
