FROM openjdk:17 as GRADLE_BUILD

COPY . .

CMD ["./gradlew", "clean", "bootJar"]

FROM openjdk:17

COPY --from=GRADLE_BUILD /build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]