#!/usr/bin/env bash

cd ~/docker
docker build -t fedml4docker .
# docker rm fedml_web
docker run -d -p 8089:8089 -v /home/dl/myProject/FedML/python:/python --name fedml_web fedml4docker