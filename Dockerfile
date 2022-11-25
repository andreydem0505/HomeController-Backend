FROM eclipse-temurin:17-jdk-focal
ADD target/HomeControllerBackend-0.0.1-SNAPSHOT.jar HomeControllerBackend-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "HomeControllerBackend-0.0.1-SNAPSHOT.jar"]
