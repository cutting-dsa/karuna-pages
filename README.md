# karuna-pages
This is the repository for the project that allows a review to ask questions and receive help from general public.
Companies can also register and get reviews from the users

## Requirements
- Java >= 8 
- MySQL 5.7

## Branches
- main : this is the master branch
- develop: this is the development branch which we mostly work with. Also feature branches come out of this one
- production: this is what we shall be deploying to online server once develop has gone through all reviews
Note: When working on a feature you branch out of `develop` and your branch could be like `develop-question` for easy identification

## Instructions how to run for local development
- Using gradle task `./gradlew clean bootRun` 
- Using executbale jar, build `./gradlew clean bootJar` to build the jar and then run jar like `java -jar build/libs/karuna-pages0.0.1-SNAPSHOT.jar` (does not require external Tomcat)
Note: Default username is `review` and password `password`

## Instructions to run using Docker and docker-compose
- Build jar file : `./gradlew clean build -x test` 
Note: ignore tests for now
- Run docker compose to start application and database containers `docker-compose up --build`
- Test by running in your Rest client `http://localhost:8080` . You should get the greeting message. Remeber the authorization.
Note: We need to externalize the credentials so they can be set by env variables and they don't appear in the committed code.

## Versions
Current version is v1.0.0

## License
