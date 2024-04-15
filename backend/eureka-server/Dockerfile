FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY eureka-server-0.0.1-SNAPSHOT.jar EurekaServer.jar
ENTRYPOINT ["java", "-jar", "EurekaServer.jar"]