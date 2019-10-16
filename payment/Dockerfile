FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9923
ARG JAR_FILE=target/payment-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} payment.jar
ENTRYPOINT ["java","-jar","payment.jar", "-Dspring.profiles.active=prod"]