FROM maven:3.5.4-jdk-8

MAINTAINER hanwu.xie xhanwu@sina.com

ENV LANG C.UTF-8

ADD ./target/friend-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 88

ENTRYPOINT ["java","-jar","/app.jar"]