#!/usr/bin/env bash

yum install -y git
yum install -y yum-utils device-mapper-persistent-data lvm2
yum install docker-ce docker-ce-cli containerd.io
yum install -y maven
# yum install -y docker-compose
yum -y install epel-release
yum -y install python-pip
pip install docker-compose
pip install --upgrade pip
# pip install -i https://pypi.tuna.tsinghua.edu.cn/simple --upgrade pip