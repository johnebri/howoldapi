# Design/Implementation details
The api is developed with Java 8 and spring boot 2.7.3. The project has the controller layer and the service layer. The controller layer accepts the request and returns the json response with the appropriate HttpStatus code. The service layer handles all the logical activities in the project. 

## How does this work
The service has one endpoint, GET /howold, which accepts the date of birth timestamp as a query parameter, dob. This timestamp is then passed on to the service layer, which calls the getAge() method. The getAge() method converts the timestamp string to a LocalDateTime, in the format yyyy-MM-dd HH:mm:ss. If the timestamp is not in the expected format, the DateTimeParseException is thrown. This exception is properly handled in the GlobalExceptionHandler class, which is annotated with @ControllerAdvice.

If the dob timestamp is in the correct format, the service then gets the difference between the date of birth and the current date and returns the difference in years. If the date of birth is after the current date/time, the service returns an error message, Date of birth should not be greater than current date/time.

A rate limiting feature is also integrated into the /howold endpoint. This feature restricts the endpoint calls to 3 calls in 1 second. If more that 3 calls are sent to the endpoint in 1 second, the RequestNotPermitted exception is thrown. This exception is handled in the GlobalExceptionHandler class, with the message "The service does not permit further calls" and http status of HttpStatus.TOO_MANY_REQUESTS.

