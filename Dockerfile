FROM maven:3.8.4-openjdk-17 as maven-builder

COPY src /app/src

COPY pom.xml /app



RUN mvn -f /app/pom.xml clean package -DskipTests

RUN chmod +x mvnw

FROM openjdk:18-jdk-slim



COPY --from=maven-builder app/target/Resolute-0.0.1-SNAPSHOT.jar /app-service/Resolute-0.0.1-SNAPSHOT.jar

WORKDIR /app-service



EXPOSE 8080

ENTRYPOINT ["java","-jar","Resolute-0.0.1-SNAPSHOT.jar"]
