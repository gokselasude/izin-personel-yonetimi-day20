# --- Aşama 1: Spring Boot uygulamasını derle ---
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle* settings.gradle* ./
RUN chmod +x gradlew

COPY src src
RUN ./gradlew build -x test --no-daemon

# --- Aşama 2: Derlenen jar'ı çalıştır ---
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
