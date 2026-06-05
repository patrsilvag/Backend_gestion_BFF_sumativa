# 1. Imagen base (Java 21)
FROM eclipse-temurin:21-jdk-alpine

# 2. Carpeta de trabajo
WORKDIR /app

# 3. Copiamos el JAR (asegúrate de que el nombre coincida con tu pom.xml del BFF)
COPY target/*.jar app.jar

# 4. Exponemos el puerto 8081 (El BFF corre en 8081, no 8082)
EXPOSE 8081

# 5. Ejecución usando el perfil 'docker'
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]