#!/bin/bash -xe

cd kafka_2.12-2.2.0

./bin/zookeeper-server-stop.sh

./bin/kafka-server-stop.sh
