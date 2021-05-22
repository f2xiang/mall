FROM java:8
EXPOSE 7000

VOLUME /tmp
ADD /target/renren-generator-1.0.0.jar  /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
