#!/usr/bin/env bash

cd ~/docker
docker build -t fedml4docker .
# docker rm fedml_web
docker run -d -p 8081:8081 -v /home/dl/myProject/FedML:/FedML --name fedml_web fedml4docker