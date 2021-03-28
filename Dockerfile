FROM openjdk:8-jdk-alpine
LABEL maintainer="mkgremo20@gmail.com"
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /main-app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]