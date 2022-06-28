package com.nguyendoha.Test_final_fis.controller.ExceptionHandler;

public class CustomerExceptionError extends RuntimeException {
    public CustomerExceptionError(String message) {
        super(message);
    }
}
