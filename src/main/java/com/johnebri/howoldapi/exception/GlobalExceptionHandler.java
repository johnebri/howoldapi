package com.johnebri.howoldapi.exception;

import com.johnebri.howoldapi.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by John on 01/09/2022
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleUndefinedExceptions(Exception ex) {
        BaseResponse br = new BaseResponse();
        br.setSuccess(false);
        br.setResponseMessage(ex.getMessage());
        return new ResponseEntity<>(br, HttpStatus.BAD_REQUEST);
    }

}
