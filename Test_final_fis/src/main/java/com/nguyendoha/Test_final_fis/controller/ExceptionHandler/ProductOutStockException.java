package com.nguyendoha.Test_final_fis.controller.ExceptionHandler;

public class ProductOutStockException extends RuntimeException {
    public ProductOutStockException(String message) {
        super(message);
    }
}
