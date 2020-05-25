FROM java:8
EXPOSE 8080 
RUN javac PessoaApplication.java
CMD java PessoaApplication
ADD /target/pessoa.jar pessoa.jar
ENTRYPOINT ["java", "-java","pessoa.jar"]
ENV JAVA_HOME C:\Program Files\Java\jdk1.8.0_161\bin