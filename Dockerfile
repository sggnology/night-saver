FROM openjdk:17

WORKDIR /app

CMD ["./gradlew", "clean", "bootJar"]

COPY build/libs/**.jar app.jar

CMD ["java", "-jar", "app.jar"]