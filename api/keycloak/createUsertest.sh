#!/bin/bash
echo "* Request for authorization"
RESULT=`curl --data "username=admin&password=admin&grant_type=password&client_id=admin-cli" http://localhost:8085/auth/realms/master/protocol/openid-connect/token`

echo "\n"
echo "* Recovery of the token"
echo $TOKEN
TOKEN=`echo $RESULT | sed 's/.*access_token":"//g' | sed 's/".*//g'`

echo "\n"
echo "* Display token"
echo $TOKEN

echo "\n"
echo " * user creation\n"
curl -v http://localhost:8085/auth/admin/realms/projet_gl/users -H "Content-Type: application/json" -H "Authorization: bearer $TOKEN"   --data '{"username":"test2","firstName":"xyz","lastName":"xyz", "email":"demo2@gmail.com", "enabled":"true"}'