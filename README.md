# url-shortner
URL shortner

Building the project
====================

Build a project using following command

`mvn clean install`

or if we want to build by skipping tests, we can do:

`mvn clean install -DskipTests`


Running a Spring Boot module
====================
To run a Spring Boot module run the command: `mvn spring-boot:run` in the module directory


Working with the IDE
====================
Import the project in IDE by selecting Existing Maven Project 


Running Tests
=============
The command `mvn clean install` run the unit tests in the project.

Building a docker image
=============
The command to build the docker image `docker build -t komal-url-shortner .`

Running a docker image
=============
The command to build the docker image `docker run komal-url-shortner`


