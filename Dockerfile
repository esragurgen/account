#FROM openjdk:17 AS build
#
#COPY pom.xml mvnw ./
#COPY .mvn .mvn
#RUN ./mvnw dependency:resolve
#
#COPY src src
#RUN ./mvnw package
#
#FROM openjdk:17
#WORKDIR account-api
#COPY --from=build target/*.jar account-api.jar
#ENTRYPOINT ["java", "-jar", "account-api.jar"]

FROM openjdk:17
WORKDIR Accounting-api
COPY target/account-0.0.1-SNAPSHOT.jar Accounting-api.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "Accounting-api.jar"]