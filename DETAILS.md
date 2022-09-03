# Design/Implementation details
The api is developed with Java 8 and spring boot 2.7.3.

# How does this work
The service has one endpoint, GET /howold, which accepts the date of birth timestamp as a query parameter, dob. This timestamp is then passed on to the service layer, which calls the getAge() method. The getAge() method converts the timestamp string to a LocalDateTime, with the format yyyy-MM-dd HH:mm:ss. If the timestamp is not in the expected format, the DateTimeParseException is thrown. This exception is properly handled in the GlobalExceptionHandler class, which is annotated with @ControllerAdvice.

If the dob timestamp is in the correct format, the service then gets the difference between the date of birth and the current date and returns the difference in years. If the date of birth is after the current date/time, the service returns an error message, "Date of birth should not be greater than current date/time".

