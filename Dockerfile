FROM docker.io/gradle:jdk25-corretto AS build

WORKDIR /app

COPY . /app

RUN gradle clean build -x test


FROM docker.io/eclipse-temurin:25.0.1_8-jdk-ubi10-minimal AS prod
LABEL authors="madhanraj-official"

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]

