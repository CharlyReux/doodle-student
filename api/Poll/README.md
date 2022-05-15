# Poll Project

## Packaging and running the application

```shell script
sudo ./mvnw package
sudo docker build -f src/main/docker/Dockerfile.jvm -t quarkus/poll-jvm .
sudo docker-compose up [--detach]
```

It is then accessible at http://localhost:8080

## Endpoints available

Everything is reacheable from "/api/poll" or for more conveniency with swagger at : http://localhost:8080/q/swagger-ui/





