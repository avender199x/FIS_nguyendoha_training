package com.fis.Sprint_4.controller.ExceptionHandler.Exception;

public class EvidenceNotFoundException extends RuntimeException{
    public EvidenceNotFoundException(String message) {
        super(message);
    }
}
