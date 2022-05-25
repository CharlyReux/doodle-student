1-Pour lancer Keycloak, écrire cette commande tout en étant dans le dossier keycloak:
```
sudo docker-compose up
```
Après ça, accéder au Keycloak Admin Console en ouvrant votre navigateur et en écrivant cet URL ``http://localhost:8080``

Si vous souhaitez exporter votre Realm mis à jour pour ensuite l'importer pour des utilisations ultérieures de Keycloak, Vous devez vous connecter en tant qu'administrateur (ici username=admin / password=admin), puis aller dans l'onglet Export.
You now have to select both "Export groups and roles" and "Export clients", then click on Export.

Il suffit maintenant de remplacer l'ancien fichier realm-export.json du dossier keycloak/imports par le nouveau tout juste téléchargé.
La prochaine fois que le docker sera démarré, il devrait y avoir le Realm mis à jour.

Pour arrêter Keycloak, utiliser la commande :  
```
sudo docker-compose down
```

2-Pour récupérer un token pour un certain utilisateur, il faut utiliser une commande curl de ce type : 

```
curl --data "grant_type=password&client_id=nginx&client_secret=U0jzcij7NIXK2R4rE6RELUpVvb4nIqRg&username=matilin.thomas@etudiant.univ-rennes1.fr&password=1234" http://localhost:8080/auth/realms/projet_gl/protocol/openid-connect/token 
```

Dans cet exemple, la commande récupère un token pour l'utilisateur matilin.thomas@etudiant.univ-rennes1.fr dans le Realm "projet_gl".

Il devrait ensuite y avoir une réponse de ce type : 
```
{"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJpTF8xcVYxWm1pSW5mZzRuVE1TbjAzQW5FakhZNU1aRXByLTBKdm1wMWtzIn0.eyJleHAiOjE2NTM0Mjg1MDMsImlhdCI6MTY1MzQyODIwMywianRpIjoiOWFiNzJiYjEtY2I3Mi00MzM3LWEwNjQtODk4OGZkY2I2ZDQ0IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL215cmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZTgyNDE1YzktZDBmNS00MmRmLWI2N2ItZGMyNTI5OTE3ODIxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoibmdpbngiLCJzZXNzaW9uX3N0YXRlIjoiOWNhOWIwNGMtZGY3ZC00ODkxLWIzY2UtY2EzZjU0NDYwMjU3IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJsb2NhbGhvc3QiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbXlyZWFsbSIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiI5Y2E5YjA0Yy1kZjdkLTQ4OTEtYjNjZS1jYTNmNTQ0NjAyNTciLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJNYXRpbGluIFRob21hcyIsInByZWZlcnJlZF91c2VybmFtZSI6Im1hdGlsaW4udGhvbWFzQGV0dWRpYW50LnVuaXYtcmVubmVzMS5mciIsImdpdmVuX25hbWUiOiJNYXRpbGluIiwiZmFtaWx5X25hbWUiOiJUaG9tYXMiLCJlbWFpbCI6Im1hdGlsaW4udGhvbWFzQGV0dWRpYW50LnVuaXYtcmVubmVzMS5mciIsInVzZXJuYW1lIjoibWF0aWxpbi50aG9tYXNAZXR1ZGlhbnQudW5pdi1yZW5uZXMxLmZyIn0.QdKr8FGcbbtSuVyMp1y19BKd8Hx640aRJ9RpbW-A4qmUwWYr9c7xJFZ3NBC0qz1-pgFSlrEs1GWzxd40PfjwQq92exnkzIHtJ80mN52kmZhjMZ5ZO8kPAJXN5BVYm0a0ElczMvDw-AoTtHkkY0c0jpW20CS9gWeK0s8737rfV1MZyd4ZAb-jKzszMaeq79kQJdbQaGTHMEbGuxF1Ne-vIfST-_dEaNf7VIbD2uWSMUsMRNZHoWPfwMVmC3pTvakyDYRqAyhZx0_jyY0geXy9Rq89IOYDCA3S5lgnrvMhQEWcZMUdQMI_wOUhkXGpXr0FKHqj0Vw_FkMWqXB6bLCzRw","expires_in":300,"refresh_expires_in":1800,"refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIxYmZmNzc4Yy04MGMzLTQzYjktODE2My1lZWY5OWEwMDdhODQifQ.eyJleHAiOjE2NTM0MzAwMDMsImlhdCI6MTY1MzQyODIwMywianRpIjoiY2RlM2RlYmItMzA5OC00ZTRhLWFhYWYtZjUwMWIxN2ZlNTFiIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL215cmVhbG0iLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvbXlyZWFsbSIsInN1YiI6ImU4MjQxNWM5LWQwZjUtNDJkZi1iNjdiLWRjMjUyOTkxNzgyMSIsInR5cCI6IlJlZnJlc2giLCJhenAiOiJuZ2lueCIsInNlc3Npb25fc3RhdGUiOiI5Y2E5YjA0Yy1kZjdkLTQ4OTEtYjNjZS1jYTNmNTQ0NjAyNTciLCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiI5Y2E5YjA0Yy1kZjdkLTQ4OTEtYjNjZS1jYTNmNTQ0NjAyNTcifQ.b1nfo2CNr0x02Xmed4_gtlh8_yfhlH67BQVMPkZoms0","token_type":"Bearer","not-before-policy":0,"session_state":"9ca9b04c-df7d-4891-b3ce-ca3f54460257","scope":"profile email"}
```

Pour créer un nouvel utilisateur, il y a cette fois-ci deux étapes :

-récupérer un token administrateur
-créer l'utilisateur grâce à ce token

Pour récupérer un token administrateur, il y a deux façons. La première est d'utiliser cette commande : 
```
curl --data "username=admin&password=admin&grant_type=password&client_id=admin-cli" http://localhost:8080/auth/realms/master/protocol/openid-connect/token
```
Celle-ci réussit à récupérer un token administrateur grâce au compte admin de keycloak.

La deuxième nécessite une configuration d'un client.
Dans ce projet, le client qui est utilisé est nginx. Il faut en revanche lui donner les droits pour que son token soit valable pour la création d'utilisateurs.
Aller dans Client, cliquer sur nginx, puis aller dans l'onglet Service Account Roles. Ici, il faut choisir realm-management dans le menu déroulant près de "Client Roles", puis sélectionner tous les rôles disponibles.

Le client nginx est maintenant capable de donner un token admin aussi. Pour ce faire, utiliser la commande : 
```
curl --data "grant_type=client_credentials&client_id=nginx&client_secret=$clé_secrète" http://localhost:8080/auth/realms/projet_gl/protocol/openid-connect/token
```
où $clé_secrète est la clé trouvable dans Client -> nginx -> Credentials. Elle a une forme comme ceci : U0jzcij7NIXK2R4rE6RELUpVvb4nIqRg

Maintenant que nous avons un token admin, il faut créer l'utilisateur.

Voici la commande : 
```
curl -v http://localhost:8080/auth/admin/realms/projet_gl/users -H "Content-Type: application/json" -H "Authorization: bearer $TOKEN"   --data '{"username":"une_adresse_mail", "enabled":"true", "credentials":[{"type":"password","value":"votre_mot_de_passe","temporary":false}]}'
```
Il est possible d'ajouter d'autres attributs à l'utilisateur comme "firstName" ou "lastName". Comme les "username" sont en fait des adresses mail, l'attribut "mail" n'a pas d'utilité ici.
