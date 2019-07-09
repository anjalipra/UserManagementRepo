# UserManagementRepo

Introduction
-------------
This Spring Boot project is created for a User Profile management system. There are 2 types of users who can access to the system with roles as ADMIN and USER. The security mechanism used to authenticate the users is JSON Web Token. Only after successful authentication they can access the REST API's. The REST endpoints have a pre-authorization wherein there is a limitation on which user can perform which API's. 

REST API's information
-----------------------
Swagger (API Blueprint) uploaded in the project at the location "UserProfileManagement\src\main\resources\Swagger_User_Management.yaml" having a detailed information about the REST API's as well as the models used.


Database
---------
This application uses H2 database, i.e. in memory database for Sprint Boot. The table schema is created at the time of running the application. I have also entered a super user having the admin rights for accessing the system for the first time. 

Tables
-------
USERS - Holding information about the user profiles
	-ID
	-DATE_OF_BIRTH
	-EMAIL
	-FIRST_NAME
	-LAST_NAME
	-HOME_ADDRESS
	-OFFICE_ADDRESS
	-USERNAME
	-PASSWORD
	
USER_ROLES - Mapping of RoleId with UserId
	-USER_ID
	-ROLE_ID
	
ROLES - Information about the roles in the system (ADMIN, USER)
	-ROLE_ID
	-ROLE_NAME
	
	
Exception Handling
-------------------
The exceptions thrown by the REST endpoints have been handled with custom exception implemented in the project.

Logging
----------
I have used log4j for logging propers loggers for debugging.

Time Taken and challenges faced
--------------------------------
The time taken by me to work on this project was 4-5 days, which basically involved implementation and upskilling myself with certain functionalities dealing in authentication and authorization using Spring Boot Security. I have implemented everything asked in the technical assessment except the docker. Though I tried to implement that as well, but I couldn't implement due to cetain challenges faced while installing the Docker in my system. Though I understood it functionally but there were software application challenges.


Why(or why not) you will build the APIs differently for web and mobile channels?
-----------------------------------------------------------------------------------
We will not build separate APIs for web and mobile channels because the REST is an architectural style which basically lets any client access the resources over http protocol. These APIs are developed for your application no matter what the client is (web / mobile).
