package com.fis.Sprint_4.controller.ExceptionHandler.Exception;

public class TrackEntryNotFoundException extends RuntimeException {
    public TrackEntryNotFoundException(String message) {
        super(message);
    }
}
