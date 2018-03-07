#!/bin/sh
mvn clean package && docker build -t com.airhacks/vacs .
docker rm -f vacs || true && docker run -d -p 8080:8080 -p 4848:4848 --name vacs com.airhacks/vacs 
