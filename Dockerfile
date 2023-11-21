FROM openjdk:17

WORKDIR /app

CMD ["./gradlew", "clean", "bootJar"]

COPY build/libs/night-saver-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]