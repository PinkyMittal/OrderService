FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8888
ARG JAR_FILE=target/catalog-0.0.1.jar
ADD ${JAR_FILE} catalog.jar
ENTRYPOINT ["java","-jar","catalog.jar", "-Dspring.profiles.active=prod"]