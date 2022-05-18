#!/bin/bash
echo "Package et conteneurisation Comment"
cd comments
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/comment-jvm .
cd ..

cd Poll
echo "Package et conteneurisation Poll"
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/poll-jvm .
cd ..

cd calendar
echo "Package et conteneurisation calendar"
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/calendar-jvm .
cd ..


echo "Lancement Docker compose"
docker-compose up
