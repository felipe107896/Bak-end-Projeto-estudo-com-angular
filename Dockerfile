FROM java:8
EXPOSE 8080 
ADD /target/pessoa.jar pessoa.jar
ENTRYPOINT ["java", "-java","pessoa.jar"]