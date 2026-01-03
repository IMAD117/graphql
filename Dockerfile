FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

RUN ./mvnw clean package -DskipTests && \
    cp target/*.jar app.jar


FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/app.jar app.jar

RUN chmod +r app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
