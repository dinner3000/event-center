FROM openjdk:8-jre

ARG JAR_FILE
COPY target/${JAR_FILE} /webapp/event-logger/event-logger.jar

ENTRYPOINT ["java", "-jar", "/webapp/event-logger/event-logger.jar", "--spring.profiles.active=test"]
