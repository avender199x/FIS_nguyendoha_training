package com.fis.Sprint_4.controller.ExceptionHandler.Exception;

public class CriminalCaseNotFoundException extends RuntimeException{
    public CriminalCaseNotFoundException(String message) {
        super(message);
    }
}
