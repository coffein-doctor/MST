FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/community-service-0.0.1-SNAPSHOT.jar CommunityService.jar
ENTRYPOINT ["java", "-jar", "CommunityService.jar"]