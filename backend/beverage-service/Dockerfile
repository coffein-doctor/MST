FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/beverage-service-0.0.1-SNAPSHOT.jar BeverageService.jar
ENTRYPOINT ["java", "-jar", "BeverageService.jar"]