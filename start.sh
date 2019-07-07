#!/bin/bash -xe

cd kafka_2.12-2.2.0

rm -rf /tmp/kafka-logs

nohup ./bin/zookeeper-server-start.sh config/zookeeper.properties > zookeeper_runlog 2>&1 &

nohup ./bin/kafka-server-start.sh config/server.properties > kafka_runlog 2>&1 &
