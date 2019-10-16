FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/order-0.0.1.jar
ADD ${JAR_FILE} order.jar
ENTRYPOINT ["java","-jar","order.jar", "-Dspring.profiles.active=prod"]