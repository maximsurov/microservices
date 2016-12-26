################################################
#             JAVA MICROSERVICE IMAGE          #
################################################
FROM java:8-jre-alpine

VOLUME /tmp

ADD spring-boot-microservice-0.1.0.jar app.jar
RUN sh -c 'touch /app.jar'

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom", "-jar", "/app.jar"]





