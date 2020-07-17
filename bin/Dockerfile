FROM postgres:9.4
EXPOSE 5434 
ADD /target/pessoa.jar pessoa.jar
ENTRYPOINT ["java", "-jar","pessoa.jar"]
ENV JAVA_HOME C:\Program Files\Java\jdk1.8.0_161\bin