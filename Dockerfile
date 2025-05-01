FROM openjdk:23-jdk
ADD target/dev-torch-api-0.0.1-SNAPSHOT.war app.war
EXPOSE 8020
ENTRYPOINT ["java", "-jar", "app.war"]