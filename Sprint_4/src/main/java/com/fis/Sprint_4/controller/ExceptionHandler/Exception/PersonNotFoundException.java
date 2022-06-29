package com.fis.Sprint_4.controller.ExceptionHandler.Exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
