FROM eclipse-temurin:17-jdk-alpine
COPY . .
EXPOSE 8080
CMD ./gradlew bootJar
ENTRYPOINT ["java","-jar","build/libs/HomeControllerBackend-0.0.1-SNAPSHOT.jar"]
