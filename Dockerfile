FROM openjdk:8-jdk-alpine
LABEL maintainer="mkgremo20@gmail.com"
VOLUME /main-app
ADD build/libs/karuna-pages-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]