# Création d'un nouveau service

Pour créer un nouveau service, il faut faire la commande suivante en changeant ServiceName par le nom du service :
```sh
    mvn io.quarkus:quarkus-maven-plugin:2.9.0.Final:create \
    -DprojectGroupId=fr.istic.tlc \
    -Dextensions="arc,flyway,config-yaml,agroal,jackson,resteasy-jackson,spring-web,jdbc-mysql,hibernate-orm-rest-data-panache,hibernate-orm,smallrye-openapi" \
    -DprojectArtifactId=ServiceName 
    
```
Il faut tout d'abords créer un fichier docker-compose.yaml dans la racine du service.
Ensuite pour la configuration de la base de données, il faut ajouter dans le docker-compose.yaml :
```yaml
version: "3.8"
services:
  dbService:
    image: mysql
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=db
      - MYSQL_USER=use
      - MYSQL_PASSWORD=pswd
    healthcheck:
      test: [ 'CMD', 'mysqladmin', 'ping', '-u', 'root', '-p root' ]
      interval: 15s
      timeout: 10s
      retries: 5
```
Il faut changer dbService par le nom de la base de données du service. Il faut aussi changer les variables dans la partie "environment". Enfin dans "Healthcheck", dans "test",
dans '-p root', il faut changer "root" par le mot de passe root que vous avez mis.

Toujours dans le docker-compose.yaml, il faut ajouter où serviceName est le nomde votre service et dbService est le nom de la base de données initialisée dans ce même fichier :
```yaml
  user:
    image: quarkus/serviceName:latest
    depends_on:
      dbService:
        condition: service_healthy
    ports:
      - "8080:8080"
    restart: on-failure
 ```
 Ensuite dans le dossier src/main/resources, il faut supprimer le fichier application.properties et créer un nouveau fichier application.yaml.
 Dans ce fichier il faut ajouter, où dans datasource le username et le password sont ceux définis précédement, pour l'url il faut changer dbService par le nom de votre base de données
 et enfin dans dans le path de swagger-ui, changez service par le nom de votre service:
 ```yaml
 quarkus:
  datasource:
    db-kind: mysql
    username: user
    password: pswd
    jdbc:
      url: jdbc:mysql://dbService:3306/user?useUnicode=true&serverTimezone=Europe/Paris
      driver: com.mysql.cj.jdbc.Driver
  flyway:
    migrate-at-start: true
    baseline-on-migrate: true
  smallrye-openapi:
    path : /swagger
  swagger-ui:
    always-include: true
    path: /swagger-ui-service
  ```
  
  Enfin dans src/main/java/istic/tlc, il faut ajouter un dossier "config" et y créer un fichier ServiceNameSwaggerConfig.java en remplaçant ServiceName par le nom de votre service.
  Dedans ajoutez le code suivant, cela va permettre d'initialiser la page swagger-ui :
  ```java
package fr.istic.tlc.config;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
    tags = {
            @Tag(name = "Service", description = "Service operations."),
    },
    info = @Info(
            title = "Service API with Quarkus",
            version = "0.0.1"
            )
)
public class ServiceSwaggerConfig {
    
}
```
Il faut maintenant changer le nom "Service" par le nom de votre service.
 
