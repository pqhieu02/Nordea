# Country API

## Overview
The purpose of this project is to demonstrate a simple example of a spring boot application that provides RESTful APIs to gather information about countries. The data is collected by fetching another service called restcountries.com

## How to run the application
To run the application locally, go to the root directory of the application and execute:
```bash
./mvnw
```

## Swagger Documentation
For easy exploration and testing of the RESTful APIs provided by this Spring Boot application, Swagger documentation is available. Once the application is running locally, you can access the Swagger UI by visiting:

`http://localhost:8080/swagger-ui/index.html`

## H2 database
This example utilise the in-memory database called H2 database. When the application is running, you can access to H2 by going to this url `http://localhost:8080/h2-console`

Note that the configuration for the H2 database must be correct in order to connect to the database:
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:country`
- User Name: `country`
- Password: (Empty)
