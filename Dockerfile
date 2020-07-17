FROM postgres:9.4
FROM java:8
FROM maven:alpine


# image layer
WORKDIR /app
ADD pom.xml /app
#RUN mvn verify clean --fail-never

# Image layer: with the application
COPY . /app
RUN mvn -v
#RUN mvn clean install -DskipTests
#RUN mvn clean spring-boot:run
EXPOSE 5434 
ADD /target/pessoa.jar pessoa.jar
ENTRYPOINT ["java", "-jar","pessoa.jar"]
ENV JAVA_HOME C:\Program Files\Java\jdk1.8.0_161\bin



