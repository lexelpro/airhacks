#!/bin/sh
mvn clean package && docker build -t com.airhacks/authentication .
docker rm -f authentication || true && docker run -d -p 8080:8080 -p 4848:4848 --name authentication com.airhacks/authentication 
