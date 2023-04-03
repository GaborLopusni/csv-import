# Csv-import-service application

### Project description
A Spring Boot service which is able to import multiple type of csv inputs into destination database using Spring Batch. The operators can send multiple files via REST to the according endpoint based on the input.

### Endpoints
3 different endpoints are available, all of them can handle multiple inputs at a time.
The input file names should match the following patterns:
1. `/api/job/policy`: `CUSTCOMP[0-9]{2}\.(TXT|txt)`
2. `/api/job/redemption`: `ZTPSPF\\.(TXT|txt)`
3. `/api/job/outpayHeader`: `OUTPH_CUP_\\d{8}_\\d{4}\\.(TXT|txt)`

The request body should be sent as form-data via POST request method with a key `files`.

The inputs will not be consumed otherwise.

### Running the tests
Unit and integration tests are also separated based on the maven profile. If `development` is the only active maven profile then only unit tests are executed.
If `integration` is also active, both unit and integration tests will be involved.

### Running the application
There are 2 predefined maven profiles which sets the `spring.profiles.active` Spring property to the proper one, so the related application properties can be loaded.
These maven profiles are `development` and `integration`. 
In case of the `development` Spring profile a Postgresql instance is configured, while the `integration` one will use an embedded H2 database.
In case of Postgresql, the password is retrieved from and environment variable named `TEST_PASSWORD`.

After packaging the application, it can be fired up with:
`java -jar -Dspring.profiles.active=development csv-import-0.0.1-SNAPSHOT.jar`
where the `-Dspring.profiles.active` system variable is optional if the project has been built with an active maven profile.

After startup the service is listening on port 8080 by default, and can be reached with the following url: `localhost:8080/api/job/outpayHeader`.
