FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/KnockKnock.war /usr/local/tomcat/webapps/
EXPOSE 8081
CMD ["catalina.sh","run"]
