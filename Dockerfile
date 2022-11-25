FROM eclipse-temurin:17-jdk-alpine
COPY . .
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/HomeControllerBackend-0.0.1-SNAPSHOT.jar"]
