FROM openjdk:8-jre
EXPOSE 8080

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ARG JAR_FILE
COPY target/${JAR_FILE} /webapp/event-api/event-api.jar

ENTRYPOINT ["java", "-jar", "/webapp/event-api/event-api.jar", "--spring.profiles.active=test"]
