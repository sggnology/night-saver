FROM openjdk:17-jdk-alpine as GRADLE_BUILD

WORKDIR /app

COPY . /app

RUN chmod +x ./gradlew
RUN ./gradlew clean
RUN ./gradlew bootjar

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=GRADLE_BUILD /app/build/libs/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]