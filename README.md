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
Run containers using docker compose
- To run application in docker containers `./gradlew clean dockerComposeUp` 
- To stop the containers `./gradlew clean dockerComposeDown`
Note: We need to externalize the credentials so they can be set by env variables and they don't appear in the committed code.

Build containers using docker
- If you want to just build container run `./gradlew clean docker` 
- By default the image will have the name defined in the docker task in build.gradle file which is karuna/karuna-pages

`docker {
    name = "karuna/karuna-pages:".plus(version)
    uri("karuna/karuna-pages:".plus(version))
    tag("name", "karuna-pages")
    buildArgs([BUILD_VERSION: 'version'])
    copySpec.from("build").into("build")
    pull(true)
    setDockerfile(file("Dockerfile"))
}`
In the docker compose we use that name like below 
`api_service:
    image: "karuna/karuna-pages:0.0.1-SNAPSHOT"
    restart: always
    ports:
      - 8080:8080
    depends_on:
      - mysql_db
    command: sh -c './wait-for mysql_db:3306 -- npm start'`
    
## Versions
Current version is v1.0.0

## License
