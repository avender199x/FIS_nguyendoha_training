package com.nguyendoha.Test_final_fis.controller.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.nguyendoha.Test_final_fis.constant.Constant.*;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            IllegalArgumentException.class
    })
    protected ResponseEntity<ErrorMessage> handleNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(NOT_FOUND).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            Exception.class
    })
    protected ResponseEntity<ErrorMessage> handleSystemError(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorMessage.builder().code(INTERNAL_SERVER_ERROR).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {CustomerExceptionError.class})
    protected ResponseEntity<ErrorMessage> CustomerExceptionError(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().code(CUSTOMER_ERROR).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {CustomerNotFoundException.class})
    protected ResponseEntity<ErrorMessage> CustomerNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(CUSTOMER_NOT_FOUND).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {OrderExceptionNotSupport.class})
    protected ResponseEntity<ErrorMessage> OrderExceptionNotSupport(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().code(ORDER_ERROR).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {ProductOutStockException.class})
    protected ResponseEntity<ErrorMessage> ProductOutStockException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                .body(ErrorMessage.builder().code(PRODUCT_OUT_STOCK).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    protected ResponseEntity<ErrorMessage> ProductNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(PRODUCT_NOT_FOUND).message(exception.getMessage()).build());
    }

}
