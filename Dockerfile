FROM openjdk:11-jre-slim
MAINTAINER Rafael Gomes <r4.gomes@gmail.com>

RUN groupadd -r spring && useradd -r -gspring spring
USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Xmx512m","-jar","/app.jar"]