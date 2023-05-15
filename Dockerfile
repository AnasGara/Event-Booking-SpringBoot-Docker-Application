FROM openjdk:10-jre-slim
WORKDIR /app
COPY ./target/*.jar /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Eventbooking-0.0.1-SNAPSHOT.jar"]