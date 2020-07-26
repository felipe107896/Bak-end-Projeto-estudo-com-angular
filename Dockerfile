FROM postgres:9.4
FROM java:8
VOLUME /tpm
ADD target/pessoa.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
FROM maven:alpine


# image layer
#


