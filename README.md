# 1. Project description

The API is built on technologies like Java 8, Spring Boot, Spring Data, and Apache Maven.
The database I am using is in-memory, H2. I've established a one-to-many relationship between User and Article. 
The User entity has fields like id, name, and age while Article has id, text, and color (enum) fields.

During start-up process, app is adding 5-10 random Users with Articles to the database. 

The API has several functions, including:
- Retrieving all Users whose age is greater than a specified value
- Retrieving all Users with Articles of a specified color value
- Retrieving unique names from Users with more than 3 Articles
- Saving User data
- Saving Article data
- Security is handled via JWT-based authentication
- Present 3 JUnit tests for the API methods, which cover the controller and service layer's methods.

# 2. Deployment instructions
### 2.1. Tech stack requirements
- IDE installed
- Maven installed (or embedded in IDE)
- JDK & JRE (at least Java 1.8)
- Postman Application installed
- Git Bash installed

### 2.2. Steps to deploy
- Clone the current repository from GitHub on local PC ([tutorial](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository))
- Open project via IDE
- Resolve dependencies and build project with **'mvn clean install'** command
- Run TestTaskApplication.java main method or via IDE configurations/plugins

### 2.3. Testing endpoints with postman
- Run Postman
- Import postman collection of http requests from ['postman'](/postman/TestCollection.postman_collection.json) root project folder in Postman ([tutorial](https://learning.postman.com/docs/getting-started/importing-and-exporting-data/))
- Send registration request under **TestCollection/authentication/** folder

> NOTIFICATION: as app is using JWT tokens for providing security restrictions it is obligatory to hit /auth/register or /auth/authenticate (if user was registered) to get JWT token. If it's impossible by any reason - you won't get access to app's endpoints so let app's author informed about such cases

- Copy the JWT token, got back as a response after previous step
- Insert this JWT token before sending any request in form placed in the right part of tab **'Authentication'** of request after choosing **'Bearer Token'** dropdown option

> NOTIFICATION: for using requests placed under **TestCollection/authentication/** folder it is not necessary 'cause they are open. Authentication request is accessible on default added to DB user or just registered user (name > login, age > password)

# 3. Contacts
On any issue - contact via GitHub https://github.com/ValentynKr