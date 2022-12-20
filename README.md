<h1 align="center">
  Cinema App
</h1>

## Description
This is a simple server-based web-application designed in accordance to Java SOLID rules and REST principles using Hibernate and Spring frameworks. Application has the following capabilities:
* Register new user and login for an existing users
* Roles for access to data as an 'ADMIN' or as a "USER"
* Manage movies, movie sessions, and cinema halls
* Bгy tickets throw a shopping cart and complete the order

For exchange information in solution used `JSON` format

# Project structure
The solution based on 3-tier architecture:
1. DAO - Data access layer;
2. Service - Business logic layer;
3. Controllers - Presentation layer.
   <img src="schema.png">

## Technologies
* JDK 11;
* Hibernate 5.4.27
* Maven 4.0.0
* MySQL 8.0.22
* Tomcat 9.0.50
* Spring 5.2.2

## How to use
1. Copy one from GitHub
3. Fill you own DB properties in `/resources/db.properties` file
4. Configure Tomcat server (it is recommended to use [version 9.0.50](https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.50/bin/))
5. User with `ADMIN` role is created automatically with the following credentials: 
   * email: `admin@i.ua` 
   * password: `admin123`
6. Use and enjoy