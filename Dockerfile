# 1단계: 빌드 환경
FROM bellsoft/liberica-openjdk-alpine:17 AS build
COPY . .
RUN ./gradlew clean build

# 2단계: 실행 환경
FROM bellsoft/liberica-openjdk-alpine:17
COPY --from=build build/libs/*.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=deploy", "-jar", "app.jar"]