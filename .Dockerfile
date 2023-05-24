FROM gradle:8.1-jdk17 AS build

WORKDIR /app

COPY . /app

RUN gradle clean build

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
