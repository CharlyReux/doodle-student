#!bin/bash

#starting the database
docker-compose -f docker-compose-test.yaml up --detach

#starting the test
mvn quarkus:dev