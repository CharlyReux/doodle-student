#!/bin/bash
echo "Package et conteneurisation Comment"
cd comments
./mvnw package -Dmaven.test.skip=true
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/comment-jvm .
cd ..

cd Poll
echo "Package et conteneurisation Poll"
./mvnw package -Dmaven.test.skip=true
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/poll-jvm .
cd ..

cd dashboard
echo "Package et conteneurisation Dashboard"
./mvnw package -Dmaven.test.skip=true
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/dashboard-jvm .
cd ..

#cd calendar
#echo "Package et conteneurisation calendar"
#./mvnw package -Dmaven.test.skip=true
#docker build -f src/main/docker/Dockerfile.jvm -t quarkus/calendar-jvm .
#cd ..

#cd mailsender
#echo "Package et conteneurisation mailsender"
#./mvnw package
#docker build -f src/main/docker/Dockerfile.jvm -t quarkus/mailsender-jvm .
#cd ..

echo "Lancement Docker compose"
docker-compose -f docker-compose-nginx.yaml down
docker-compose -f docker-compose-nginx.yaml up
