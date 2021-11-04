FROM openjdk:8-jdk-alpine
MAINTAINER bycoders.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} parserTransations.jar
ENTRYPOINT ["java", "-jar", "/parserTransations.jar"]
