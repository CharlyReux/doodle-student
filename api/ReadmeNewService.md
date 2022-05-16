# Création d'un nouveau service

Pour créer un nouveau service, il faut faire la commande suivante en changeant ServiceName par le nom du service :
```sh
    mvn io.quarkus:quarkus-maven-plugin:2.9.0.Final:create \
    -DprojectGroupId=fr.istic.tlc \
    -Dextensions="arc,flyway,config-yaml,agroal,jackson,resteasy-jackson,spring-web,jdbc-mysql,hibernate-orm-rest-data-panache,hibernate-orm,smallrye-openapi" \
    -DprojectArtifactId=ServiceName 
    
```
Dans le fichier POM.xml, retirez la dépendance resteasy-reactive-jackson
```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-resteasy-reactive-jackson</artifactId>
</dependency>
```

Il faut tout d'abord créer un fichier docker-compose.yaml dans la racine du service.
Ensuite pour la configuration de la base de données, si vous en avez besoin, il faut ajouter dans le docker-compose.yaml :
Il faut changer dbService par le nom de la base de données du service. Il faut aussi changer les variables dans la partie "environment". Enfin dans "Healthcheck", dans "test", dans '-p root', il faut changer "root" par le mot de passe root que vous avez mis.
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

Toujours dans le docker-compose.yaml, il faut ajouter le code suivant, où serviceName est le nom de votre service et dbService est le nom de la base de données initialisée dans ce même fichier :
```yaml
  serviceName:
    image: quarkus/serviceName:latest
    depends_on:
      dbService:
        condition: service_healthy
    ports:
      - "8080:8080"
    restart: on-failure
 ```

Ensuite dans le dossier src/main/resources, dans le fichier "application.yml", il faut ajouter, où dans la partie datasource,  username est la valeur de MYSQL_USER et password est la valeur de MYSQL_PASSWORD,définis précédement. Pour l'url il faut changer dbService par le nom de votre base de données, et enfin dans dans le path de swagger-ui, changez "service" par le nom de votre service:
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
Dedans ajoutez le code suivant, cela va permettre d'initialiser la page swagger-ui, il faut changer le nom "Service" par le nom de votre service. :
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
 
Enfin dans src/main/java/istic/tlc, il faut créer au moins 3 dossiers :
- un dossier "dao", où vous mettrez le fichier repository pour la base de donnée (si nécessaire) avec un fichier ServiceRepository.java qui ressemble à cela où "service" est le nom de votre service :
```java
package fr.istic.tlc.dao;

import javax.enterprise.context.ApplicationScoped;

import fr.istic.tlc.domain.Service;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ServiceRepository implements PanacheRepository<User> {
}
```
- un dossier "domain", où vous mettrez le(s) entity(ies)
- un dossier "resources", qui n'est pas le même que celui dans main/resources, où vous mettrez le(s) fichier(s) avec les apis
