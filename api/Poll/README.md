# Poll Project

## Packaging and running the application

```shell script
sudo ./mvnw package
sudo docker build -f src/main/docker/Dockerfile.jvm -t quarkus/poll-jvm .
sudo docker-compose up [--detach]
```

It is then accessible at http://localhost:8080

## Endpoints available

Everything is reacheable from "/api/poll"


#### tests endpoints
> "/hello" : test function that returns "hello world!"

> "/json/{test} : test function that returns a test poll

### usable endpoints
#### Get
> "/all" : return a list of all polls created
#### Post
> "/polls" : creates a new poll in the database and returns it<br>
> example : curl -H "Content-Type: application/json" -X POST -d '{"title":"test"}' http://localhost:8080/api/poll/polls




