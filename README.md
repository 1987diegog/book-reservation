# Spring-boot (Api REST) 
#### Book-Reservation
Proyecto a modo de ejemplo que expone una API *REST*, se utiliza H2 para la BD.
El proyecto cuenta con una batería de test TDD para probar cada servicio expuesto

## Servicios
### POST

    /rest-jax-rs-resteasy/rest/persons

    {
    "name":"Diego", 
     "lastName":"Gonzalez", 
     "streetAddress":"Rambla", 
     "age":"32", 
     "cellPhone":"099123456"
    }

###  GET

    /rest-jax-rs-resteasy/rest/persons/15

### PUT

    /rest-jax-rs-resteasy/rest/persons
    
    {
        "name":"Diego", 
         "lastName":"Gonzalez", 
         "streetAddress":"Rambla (Uruguay)", 
         "age":"32", 
         "cellPhone":"099987654"
    }
    
### DELETE

    /rest-jax-rs-resteasy/rest/persons/15

## Resumen:

- **BD**
	- *h2*

- **TDD**
	- *Junit*

- **Servidor embebido**
	- *apache*

- **Java 8**
	- *Lambdas*
	- *Stream*
	- *Java Generics*

- **Otros**
	- *Data Transfer Object - DTO*
	- *Data Acces Object - DAO*
	- *Arquitectura REST*
	- *Json*
	- *Swagger*
