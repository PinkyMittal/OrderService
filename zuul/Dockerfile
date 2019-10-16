FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8762
ARG JAR_FILE=target/zuul-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} zuul.jar
ENTRYPOINT ["java","-jar","zuul.jar", "-Dspring.profiles.active=prod"]