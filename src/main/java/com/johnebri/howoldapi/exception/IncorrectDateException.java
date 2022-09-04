package com.johnebri.howoldapi.exception;

/**
 * Created by John on 04/09/2022
 */
public class IncorrectDateException extends RuntimeException {
    public IncorrectDateException(String message) {
        super(message);
    }
}
