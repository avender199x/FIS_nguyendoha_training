package com.fis.Sprint_4.controller.ExceptionHandler.Exception;

public class DetectiveNotFoundException extends RuntimeException {
    public DetectiveNotFoundException(String message) {
        super(message);
    }
}
