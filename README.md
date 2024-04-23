# FiiPractic-Final-project (Twitter REST-API)

* [Description](#description) 
* [Setup](#setup)
* [Overview](#overview)
    * [Database](#database)
    * [Backend](#backend) 
* [Future Plans](#future-plans)



## Description

In the FiiPractic 2024 project, within the Java Enterprise Applications with Spring course organized by Cognizant, we implemented a REST API aiming to cover several functionalities akin to those of the former Twitter (such as posting, commenting on posts, sharing posts, mentions, etc.).

![image](https://github.com/Bordei08/FiiPractic-final-project/assets/79217056/b7a28d21-ffa7-43d7-896e-a9011f5b2c75)

## Setup
The setup for this project is quite simple. I'm using a PostgreSQL database and a Java Spring Boot application. The script for creating the database and tables is located in the 'Database' folder. The Java application is configured as follows:
 * Java 17
 * Maven 4.0.0
 * Spring Boot 3.2.4

```
spring.application.name=FiiPracticFinalProject
application-description="Twitter app Spring Doc for SpringBoot 3"
application-version=1.0.0
#Data base config
spring.datasource.url=jdbc:postgresql://localhost:5432/FiiPractic
spring.datasource.username= postgres
spring.datasource.password= *

```

## Overview
This project consists of two components: the database and the Java application.
### Database
I'm working with a relational database built on PostgreSQL, which includes 6 tables. Additionally, I've implemented 3 triggers for more efficient table management. The scripts for tables and triggers are available in the 'Database' directory. Moreover, I've included an ERD diagram to better visualize the relationships between tables.


![image](https://github.com/Bordei08/FiiPractic-final-project/assets/79217056/b240b5dc-afb0-4ae4-84a4-b9f2d2479a56)

### Backend
The backend is developed in Java, using the Spring Boot framework, along with other technologies such as Spring Security, OpenAPI, and JDBC for database interaction. I have personally implemented 41 endpoints, detailed in the Swagger documentation. This documentation can be accessed after starting the application and visiting the http://localhost:8080/swagger-ui/index.html.

## Future Plans
The creation of a graphical interface, preferably a web interface, implemented using the React framework.


