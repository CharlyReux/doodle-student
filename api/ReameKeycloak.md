to run the keycloak server, go to the quarkus-quickstarts: use this command
```
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev
```

if you have permission issues, use this command before :
```
sudo chmod 666 /var/run/docker.sock
```
