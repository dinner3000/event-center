FROM openjdk:8-jre

ARG JAR_FILE
COPY target/${JAR_FILE} /webapp/event-forwarder/event-forwarder.jar

ENTRYPOINT ["java", "-jar", "/webapp/event-forwarder/event-forwarder.jar", "--spring.profiles.active=test"]
