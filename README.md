# spring-keycloak-demo
A simple demo for showing how keycloak can be used to secure a REST API.

## Start the REST service
When the application starts, the keycloak server will automatically start like defined in the docker-compose.yaml

## Setup
After the app started, you have to confiugre keycloak like following:

- Create a realm 'petstore'
- Create a client 'petstore-app' (maybe not the best name :D)
- Add the roles 'petstore_admin' and 'petstore_user' to the client
- Create a user which only has the role 'petstore_user'
- Create an admin user which also has the role 'petstore_admin'

## Call the service
To call the service successfully, we have to obtain a token and call the service afterwards:

1. Make a POST request to 'http://localhost:8010/realms/petstore/protocol/openid-connect/token' and add the following headers:
    1. grant_type = password
    2. client_id = petstore-app
    3. username = >>one of the users from above<<
    4. password = >>the password of the user<<
2. Copy the access_token from the reponse
3. Make a GET to 'http://localhost:8080/api/petstore/v1/pets' and add the token as 'Bearer Token' to the request
4. Make a POST to 'http://localhost:8080/api/petstore/v1/pets' and add the token as 'Bearer Token' to the request

If you use both users to do this, you will see, that only the admin is able to make POST requests.