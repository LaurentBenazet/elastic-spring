FROM openjdk:17-jdk-alpine
MAINTAINER laurent
COPY target/spring-elastic-0.0.1-SNAPSHOT.jar spring-elastic.jar
ENTRYPOINT ["java","-jar","/spring-elastic.jar"]