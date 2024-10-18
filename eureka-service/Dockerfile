ARG SERVICE_NAME=eureka-service
FROM openjdk:21-slim AS jdk
LABEL authors="Hugo Saldarriaga"

ARG SERVICE_NAME

# Create app directory
WORKDIR /app/${SERVICE_NAME}

# Copy necessary Gradle files including the wrapper
COPY ./gradlew /app/gradlew
COPY ./gradlew.bat /app/gradlew.bat
COPY ./gradle/wrapper /app/gradle/wrapper
COPY ./.gradle /app/.gradle
COPY ./build.gradle.kts /app
COPY ./settings.gradle.kts /app

# Copy project-specific files
COPY ./${SERVICE_NAME}/.gradle ./.gradle
COPY ./${SERVICE_NAME}/gradle ./gradle
COPY ./${SERVICE_NAME}/build.gradle.kts .
COPY ./${SERVICE_NAME}/gradlew .
COPY ./${SERVICE_NAME}/gradlew.bat .
COPY ./${SERVICE_NAME}/settings.gradle.kts .

# Use the Gradle wrapper to clean and build the project
RUN ./gradlew clean build -x test -x compileJava -x bootJar

# Copy the source code
COPY ./${SERVICE_NAME}/src ./src

# Build the project again to ensure all is set (may be redundant)
RUN ./gradlew clean build -x test

FROM openjdk:21-slim

ARG SERVICE_NAME

WORKDIR /app

RUN mkdir ./logs

# Copy the built JAR from the previous stage
COPY --from=jdk /app/${SERVICE_NAME}/build/libs/${SERVICE_NAME}-0.0.1-SNAPSHOT.jar .

ENV PORT=8001

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "eureka-service-0.0.1-SNAPSHOT.jar"]