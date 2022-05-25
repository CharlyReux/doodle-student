# Doodle en Quarkus avec microservices

Ce repository est un fork du projet doodle fait avec quarkus.io, qui a pour but de changer son architecture en microservices.

Cette nouvelle architecture se trouve dans le dossier /api, et l'ancienne dans /api_old.

## Architecture

La nouvelle architecture se compose de ceci :
 - un Front en Angular

 - une API Gateway en NGinx qui est liée à : 
  - un service d'authentification avec Keycloak
  - un microservice Dashboard
  - un microservice Poll

Poll est le microservice qui permet la création des polls, et a accès à : 
 - un microservice Chat
 - un microservice EtherPad
 - un microservice Comment

Le service d'authentification permet d'empêcher les utilisateurs inconnus d'accéder au service Dashboard, qui permet de retrouver les historiques des polls créés par les utilisateurs ayant un compte.
Quand il y a une demande de création de poll, le Front envoie sa demande à l'API Gateway, qui va elle envoyer une requête à Poll. Poll crée un poll dans sa base de données, et appelle Chat, EtherPad et Comment pour les lier au poll généré.
Quand une personne se connecte ou crée un compte, l'API Gateway redirige les requêtes vers Keycloak, pour que celui-ci envoie un token quand il s'agit d'une connexion, ou ajoute l'utilisateur dans sa BDD quand il s'agit d'une création.

# Lancement du projet

Ce projet a été créé avec :
- java openjdk 11.0.15
- maven
- docker 20.10.12
- docker-compose version 1.29.2

Pour pouvoir lancer le projet, vous devez allez dans le répertoire doodlestuden/api et lancer la commande suivante :
```sh
sudo bash PackageAndStart.sh
```

Ensuite, allez dans le répertoire doodle/front et lance les commandes suivantes :
```sh
npm install
npm start
```

Enfin pour accéder au doodle, dans votre navigateur, entrez l'url suivante localhost:4200
