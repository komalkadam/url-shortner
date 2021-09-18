FROM openjdk:8-jdk-alpine
LABEL maintainer "komalrkadam[at]gmail[dot]com"
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} urlshortner.jar
ENTRYPOINT ["java","-jar","/urlshortner.jar"]
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
    CMD curl -s http://localhost:8080 || exit 1
