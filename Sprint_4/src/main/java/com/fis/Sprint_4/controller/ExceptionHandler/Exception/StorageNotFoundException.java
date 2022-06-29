package com.fis.Sprint_4.controller.ExceptionHandler.Exception;

public class StorageNotFoundException extends RuntimeException {
    public StorageNotFoundException(String message) {
        super(message);
    }
}
