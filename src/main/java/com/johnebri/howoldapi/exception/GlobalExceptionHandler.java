package com.johnebri.howoldapi.exception;

import com.johnebri.howoldapi.dto.BaseResponse;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

/**
 * Created by John on 01/09/2022
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<BaseResponse> handleUndefinedExceptions(RequestNotPermitted ex) {
        BaseResponse br = new BaseResponse();
        br.setSuccess(false);
        br.setResponseMessage("The service does not permit further calls");
        return new ResponseEntity<>(br, HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<BaseResponse> handleUndefinedExceptions(DateTimeParseException ex) {
        BaseResponse br = new BaseResponse();
        br.setSuccess(false);
        br.setResponseMessage("Invalid timestamp format. Please specify date of birth in this format: yyyy-mm-dd HH:mm:ss. Example 1991-12-19 11:11:11");
        return new ResponseEntity<>(br, HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleUndefinedExceptions(Exception ex) {
        BaseResponse br = new BaseResponse();
        br.setSuccess(false);
        br.setResponseMessage(ex.getMessage());
        return new ResponseEntity<>(br, HttpStatus.BAD_REQUEST);
    }

}
