# FROM maven:3.8.4-openjdk-17-slim

# WORKDIR /app

# COPY pom.xml .
# COPY src ./src

# RUN mvn clean package

# EXPOSE 8080

# CMD java -jar target/my-app-0.0.1-SNAPSHOT.jar

#
# Build
# #
# FROM maven:3.8.7-openjdk-18-slim AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package
# #
# # Package stage
# #
# FROM openjdk:11-jre-slim
# COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
# ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]

FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
