FROM openjdk:19 as builder

WORKDIR /citzensDemo
CMD ["./gradlew", "clean", "bootJar"]



ENTRYPOINT ["java", "-jar", "app.jar"]