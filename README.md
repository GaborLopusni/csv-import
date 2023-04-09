# Csv-import-service application

### Project description
A Spring Boot service which is able to import multiple type of csv inputs into destination database using Spring Batch. The operators can send multiple files via REST to the according endpoint based on the input.

### Requirements
1. JDK 17+
2. Maven
3. Postgresql [optional]
### Endpoints
3 different endpoints are available, all of them can handle multiple inputs at a time.
The input file names should match the following patterns:
1. `/api/job/policy`: `CUSTCOMP[0-9]{2}\.(TXT|txt)`
2. `/api/job/redemption`: `ZTPSPF\\.(TXT|txt)`
3. `/api/job/outpayHeader`: `OUTPH_CUP_\\d{8}_\\d{4}\\.(TXT|txt)`

The request body should be sent as form-data via POST request method with a key `files`.

The inputs will not be consumed otherwise.

### Running the application and tests

There are 2 predefined maven profiles which sets the `spring.profiles.active` Spring property to the proper one, with related application properties:
1. `development`: Postgresql instance is configured, only unit tests are executed. Data source password can be set with the `TEST_PASSWORD` environment variable.
2. `integration`: H2 embedded database is configured, both unit and integration tests are executed.

After packaging the application, it can be fired up with:
`java -jar -Dspring.profiles.active=development csv-import-0.0.1-SNAPSHOT.jar`
where the `-Dspring.profiles.active` system variable is optional if the project has been built with an active maven profile.

After startup the service is listening on port 8080 by default, and can be reached with the following url: `localhost:8080/api/job/outpayHeader`.

### Running with docker

Docker image can be built with integration active profile using the following mvn command:
`mvn spring-boot:build-image -Pintegration`

After the image has been built, it can be run by:
`docker run -d -p 8080:8080 csv-import:1.0.0` which exposes the container port 8080 to localhost:8080.

### Send multipart files via POST

Reference: https://www.postman.com/postman/workspace/postman-answers/documentation/13455110-00378d5c-5b08-4813-98da-bc47a2e6021d
