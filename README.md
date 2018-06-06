# discuss-now
Discuss now is a platform to discuss anything just like a forum. This application is built using Spring Boot and Hibernate. Platform supports Google OAuth as well.

Setup Instructions :

1. Clone the repository.
2. Install Any Relational database on your local machine.
3. Create an app in the google developer console.
4. copy the client id and client secret from google developers website (for the app that you have created in step 3) and paste it in the   application.yml file
5. create an user for the relational database with password and update the same in the application.yml file
6. Replace database connector dependency with the dependency that is specific to the database you are using in pom.xml file
7. Run the DiscussnowApplication.java file and it will start the server at http://localhost:8080 

Note : The application has basic UI for the Topic page. But this is completely customizable with your own front end tech stack.
