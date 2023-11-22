#FROM openjdk:17
#
#WORKDIR /app
#
#CMD ["./gradlew", "clean", "bootJar"]
#
#COPY build/libs/*.jar app.jar
#
#CMD ["java", "-jar", "app.jar"]

FROM openjdk:17 as GRADLE_BUILD

WORKDIR /app

COPY . /app

RUN chmod +x ./gradlew
RUN ./gradlew clean
RUN ./gradlew bootJar

FROM openjdk:17

WORKDIR /app

COPY --from=GRADLE_BUILD /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
