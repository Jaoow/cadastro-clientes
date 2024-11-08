# Etapa 1: Construção do WAR
FROM maven:3.8.7-eclipse-temurin-8 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Configuração do Tomcat com o WAR
FROM tomcat:9.0.96-jdk8-corretto
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"
COPY --from=build /app/target/client-registry-webapp.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
