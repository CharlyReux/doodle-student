#!/bin/sh

./mvnw package

docker build -f src/main/docker/Dockerfile.jvm -t quarkus/user-jvm .
