package com.nguyendoha.Test_final_fis.controller.ExceptionHandler;

public class OrderExceptionNotSupport extends RuntimeException {
    public OrderExceptionNotSupport(String message) {
        super(message);
    }
}
