FROM openjdk:8-jre

ARG JAR_FILE
COPY target/${JAR_FILE} /webapp/event-generator/event-generator.jar

ENTRYPOINT ["java", "-jar", "/webapp/event-generator/event-generator.jar", "--spring.profiles.active=test"]
