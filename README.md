# 23peopleTest

Test with OAuth2 and JWT

Proyecto cuya findalidad es exponer una API RESTFul con seguridad de autenticacion y autorizacion.

## Tecnologías ocupadas en el desarrollo:

* Spring Framework 
* Tomcat (Embebido)
* Spring Boot
* Spring Data JPA
* Spring WEB
* Spring Security
* Hibernate como persistencia
* H2 DataBase 
* Lombok
* Maven
* Java 8

## Instalacion:
 
Se debe clonar el repositorio GitHub, luego importarlo como un proyecto como Gradle , y luego se debe compilar el proyecto con build que facilita el IDE utilizado.

Para clonar el repositorio debemos abrir una consola y luego escribir git clone <nombre del repositorio> ej:
	
	* git clone git@github.com:fegalaz/23peopleTest.git

## DataBase

La base de datos se crea al momento de correr la aplicacion , la estructura tambien se crea al momento de correr la aplicacion , la estructura la cual va a ser utilizada por la api de creacion de usuarios se encuentra en la siguiente ruta:

* /GlobalLogic/src/main/resources/data.sql

`drop table if exists oauth_access_token;
create table oauth_access_token(
  token_id          VARCHAR(255),
  token             varbinary(4096),
  authentication_id VARCHAR(255),
  user_name         VARCHAR(255),
  client_id         VARCHAR(255),
  authentication    varbinary(4096),
  refresh_token VARCHAR(255)
);`

`drop table if exists auth_user;
create table auth_user(
	id INT AUTO_INCREMENT,
	username VARCHAR(255),
	password VARCHAR(255),
	email VARCHAR(255),
	mobile VARCHAR(255)
);`

`drop table if exists AUTH_ROLE;
create table AUTH_ROLE(
	  id          bigint(20) NOT NULL,
	  role_name   VARCHAR(255),
	  description VARCHAR(255)
);`

`drop table if exists oauth_refresh_token;
create table oauth_refresh_token(
  token_id        VARCHAR(255),
  token           varbinary(4096),
  authentication  varbinary(4096)
);`

`drop table if exists oauth_code;
create table oauth_code(
  code            VARCHAR(255),
  authentication  varbinary(4096)
);`

`drop table if exists oauth_approvals;
create table oauth_approvals(
  userId         VARCHAR(255),
  clientId       VARCHAR(255),
  scope          VARCHAR(255),
  status         VARCHAR(10),
  expiresAt      TIMESTAMP,
  lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);`

`drop table if exists ClientDetails;
create table ClientDetails(
  appId                  VARCHAR(255) PRIMARY KEY,
  resourceIds            VARCHAR(255),
  appSecret              VARCHAR(255),
  scope                  VARCHAR(255),
  grantTypes             VARCHAR(255),
  redirectUrl            VARCHAR(255),
  authorities            VARCHAR(255),
  access_token_validity  INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation  VARCHAR(4096),
  autoApproveScopes      VARCHAR(255)
);`

`create index oauth_access_token_id on oauth_access_token(token_id);
create index oauth_refresh_token_id on oauth_access_token(token_id);`



--START CLIENT CREDENTIAL TABLES--

`drop table if exists oauth_client_details;
create table oauth_client_details(
  client_id               VARCHAR(256) PRIMARY KEY,
  resource_ids            VARCHAR(256),
  client_secret           VARCHAR(256),
  scope                   VARCHAR(256),
  authorized_grant_types  VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities             VARCHAR(256),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(256)
);`

`drop table if exists oauth_client_token;
create table oauth_client_token(
  token_id          VARCHAR(255),
  token             varbinary(4096),
  authentication_id VARCHAR(255),
  username         VARCHAR(255),
  client_id         VARCHAR(255)
);`

`drop table if exists user_role;
CREATE TABLE user_role (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL
);`

-- #Tables for course and students# --

`DROP TABLE if exists courses; 
CREATE TABLE courses (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  code VARCHAR(4) NOT NULL
);`

`DROP TABLE IF EXISTS students;
CREATE TABLE students (
  rut VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL,
  lastname VARCHAR(250) DEFAULT NULL,
  age int DEFAULT NULL,
  courseId int DEFAULT NULL
);`

-- ############################################################################################# --

-- BASIC AUTH with token time declarationuser_role
-- ACCESS TOKEN VALIDITY = 300 SECOND
-- REFRESH TOKEN VALIDITY = 1800 SECOND
-- insert client details [clientId = test & clientSecret = temp]

`INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
authorities, access_token_validity, refresh_token_validity)
VALUES ('test', '$2a$10$qgfrPSuoOvcoTYW1oka1r.XuQ67t9tt6erpZ4pa3/rx4Np0EF.fB6',
'read,write', 'password,refresh_token,client_credentials,authorization_code',
'ROLE_TRUSTED_CLIENT', 300, 1800);`


-- USER ROLES

`INSERT INTO auth_role(id, role_name, description)
VALUES (1, 'ROLE_ADMIN', 'Admin User - Has permission to perform admin tasks');`

`INSERT INTO auth_role(id, role_name, description)
VALUES (2, 'ROLE_USER', 'CONSULTANT - Has no admin rights');`

`INSERT INTO auth_user(id,username,password,email,mobile)
VALUES (1,'felipe','$2a$10$/1Gl2B/Tjg5.yV.sb62uHeM.pKRViSuwMaQe0GjaiPuaRhBfqTOP6','felipe@admin.com','123456789');`

Ademas el proyecto contiene un YAML de configuracion el cual deberia ser modificado la ruta del h2 file

Application.ylm

`spring:
    datasource:
        driverClassName: org.h2.Driver
        password: " "
        url: jdbc:h2:file:C:/Users/Felipe Galaz/test
        username: "sa"
    h2:
        console:
            enabled: true
            path: /h2-console
            settings:
                trace: true
                web-allow-others: false
    jpa:
        database-platform: org.hibernate.dialect.H2Dialect
        hibernate:
            ddl-auto: none
        show-sql: true
security:
  oauth2:
    resource:
      filter-order: 3
  signing-key: temp
jwt:
    secret: temp`

El tag `url : jdbc:h2:file:C:/Users/Felipe Galaz/test` deberia ser modificado por una ruta de la maquina donde se levantara dicho proyecto.

## Despliegue

* Desde consola
	Para poder desplegar el proyecto por consola debemos posicionarnos dentro de la carpeta del proyecto y ejecutar el comando *mvn clean package*

* Desde IDE
	Para poder desplegar el proyecto a traves del IDE , si ya tenemos importado el proyecto solo debemos hacer click en *run*

Una vez que el proyecto este corriendo en la maquina local , la aplicacion sera levanta en el puerto *8080* para probar que la API RestFul funciona correctamente debemos ejecutar el JSON como metodo POST con algun IDE que envie peticiones HTTP , yo ocupo postman.


* Metodo que guardara un usuario para poder autenticarse.

* URL : localhost:8080/oauth/users

* Method : POST

# JSON Request
`{
    "username": "andres",
    "password": "olv2vx105",
    "email": "andres@23people.com",
    "mobile": "123456789"
}`


# JSON Respond
`{
    "id": 2,
    "username": "andres",
    "password": "$2a$10$xR4N/aUMEemPzoKTLrRayuH3av/qlOGOXcC53uhpwWjH6u.jNq8oC",
    "email": "andres@23people.com",
    "mobile": "123456789",
    "roles": [
        {
            "id": 2,
            "roleName": "ROLE_USER",
            "description": "CONSULTANT - Has no admin rights"
        }
    ]
}`



* Metodo que obtendra un token para poder autorizar.

* URL : localhost:8080/oauth/token?username=andres&password=olv2vx105&grant_type=password

* Method : GET

# Request Params

`   "username": "andres"
    "password": "olv2vx105"
    "grant_type":password"
`
 Authorization : Basic Auth 
 
  *"username": "test"
   "password": "temp"*

# JSON Respond

`{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDA5NjE3NTMsInVzZXJfbmFtZSI6ImFuZHJlcyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJlNTczYzcyNC0xZjEzLTQzNTMtYTEwMi1hYjhjN2ZkY2JhNGMiLCJjbGllbnRfaWQiOiJ0ZXN0Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.rXfl6XlYhtcJD8HZCua7MXEbGTKJCQQkVKg3hdnuY8w",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhbmRyZXMiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiZTU3M2M3MjQtMWYxMy00MzUzLWExMDItYWI4YzdmZGNiYTRjIiwiZXhwIjoxNjAwOTYzMjUzLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiNDU0MzdhZDAtYzRiZC00Y2Q4LThiZDItZDBhNDUwZTdjOWJjIiwiY2xpZW50X2lkIjoidGVzdCJ9.JwlU2gghiKrgY-Sdn7b5lS67cKJWMaG35AZhtrO2-nE",
    "expires_in": 298,
    "scope": "read write",
    "jti": "e573c724-1f13-4353-a102-ab8c7fdcba4c"
}`

Ya una vez autorizado el usuario con el token activo podra utilizar los recursos dado su *ROLE*


## Autores

*Felipe Galaz*

