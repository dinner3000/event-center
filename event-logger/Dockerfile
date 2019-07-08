FROM openjdk:8-jre
#EXPOSE 8081

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/lib           /usr/share/webapp/event-logger/lib

# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/webapp/event-logger/event-logger.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/webapp/event-logger/event-logger.jar"]
