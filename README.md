ESTA ES LA PRACTICA DE JAVA, PARA SU CORRECTO FUNCIONAMIENTO PRIMERO EJECUTAR EL AUTH SERVER Y LUEGO EL CLIENTE.

USUARIO: pepe
contraseña: 12345


Callback URL
http://127.0.0.1:8080/login/oauth2/code/client-app
Auth URL
http://127.0.0.1:9000/oauth2/authorize
Access Token URL
http://127.0.0.1:9000/oauth2/token
Client ID
client-app
Client Secret
12345
scope
read

endpoint: GET
http://localhost:8080/catalogos/carros

endpoint: GET
http://localhost:8080/catalogos/carros/10

endpoint: GET
http://localhost:8080/catalogos/carros/marca/Nissan

endpoint: post
http://localhost:8080/catalogos/carros/create
scope: write

endpoint: delete
http://localhost:8080/catalogos/carros/delete/10

endpoint: update
http://localhost:8080/catalogos/carros/update/10

endpoint: GET
http://localhost:8080/api/pokemon/dito

endpoint: GET
http://localhost:8080/api/pokemon/encrypted/dito
