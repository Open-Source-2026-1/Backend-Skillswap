# Fase de compilación usando Maven y Java 26 experimental
FROM maven:3.9.6-eclipse-temurin-26-alpine AS build
WORKDIR /app
COPY . .
RUN mnv clean package -DskipTests

# Fase de ejecución
FROM eclipse-temurin:26-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8097
ENTRYPOINT ["java", "-jar", "app.jar"]
