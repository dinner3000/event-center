FROM openjdk:8-jre

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ARG JAR_FILE
COPY target/${JAR_FILE} /webapp/event-generator/event-generator.jar

ENTRYPOINT ["java", "-jar", "/webapp/event-generator/event-generator.jar", "--spring.profiles.active=test"]
