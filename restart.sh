#!/bin/bash -xe

docker-compose -p eventcenter stop
docker-compose -p eventcenter start
