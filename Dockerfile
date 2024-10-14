FROM --platform=linux/amd64 openjdk:21
# VOLUME /tmp
EXPOSE 8080
COPY target/IlpTutorial3-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]