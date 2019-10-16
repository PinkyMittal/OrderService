FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8085
ARG JAR_FILE=target/inventory-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} inventory.jar
ENTRYPOINT ["java","-jar","inventory.jar", "-Dspring.profiles.active=prod"]