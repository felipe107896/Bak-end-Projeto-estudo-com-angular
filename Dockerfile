FROM java:8
EXPOSE 8080 
ADD /target/pessoa.jar pessoa.jar
ENTRYPOINT ["java", "-jar","pessoa.jar"]
ENV JAVA_HOME C:\Program Files\Java\jdk1.8.0_161\bin