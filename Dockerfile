FROM openjdk:11
LABEL maintainer="bob.sakawa@gmail.com"
VOLUME /tmp
ADD target/toqio-banking-0.0.1-SNAPSHOT.jar toqio-banking-0.0.1-SNAPSHOT.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","toqio-banking-0.0.1-SNAPSHOT.jar"]