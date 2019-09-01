# README:


The project That I develop is a Spring boot project with support for JPA with H2 memory DB. However I code some configuration classes to support other DB with a Connection factory and Data Source configuration.

Into this project I configured the checkstyle  in order to keep the code clean and force the developer to add comments, format the code etc. The checkstyle will run during the mvn clean install process. If the plugin find any error regarding style it will not compile property.

I also configure the swagger console to see the documentation for the API’s and have the possibility to execute the API´s as the requirements asked

To run the app be sure that you are working with the java 8 version and use the executable Jar located at the root folder at the same level of the present README file with name:


products-service-0.0.1-SNAPSHOT.jar

Open your favourite console and execute it with the following command under the same path of the file:

java -jar products-service-0.0.1-SNAPSHOT.jar

Then you will be able to use the H2 DB going to the following URL 

http://localhost:8080/h2-console/

JDBC URL: jdbc:h2:mem:products_QA
use the username: centric 
Password: 

To execute the API you can use the swagger console in this URL:

http://localhost:8080/swagger-ui.html
