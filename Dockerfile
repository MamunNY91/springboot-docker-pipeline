FROM openjdk:8-jre-alpine
EXPOSE 8080
COPY ./target/spring-boot-docker-pipeline-*.jar /usr/app/
WORKDIR /usr/app
CMD java -jar spring-boot-docker-pipeline-*.jar
