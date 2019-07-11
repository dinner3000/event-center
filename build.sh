#!/bin/bash -ex

JAR_SUFFIX="boot.jar"

#APP_LIST="event-api event-logger event-forwarder"
APP_LIST="event-logger event-forwarder event-api event-generator"

VERSION="1.0.0-SNAPSHOT"

IMAGE_PROJ="ucsp"

mvn clean install

for APP in ${APP_LIST}; do
	cd ${APP}
	docker build . --build-arg JAR_FILE=${APP}-${VERSION}-${JAR_SUFFIX} -t ${IMAGE_PROJ}/${APP}:${VERSION} -t ${IMAGE_PROJ}/${APP}:latest
	cd -
done


