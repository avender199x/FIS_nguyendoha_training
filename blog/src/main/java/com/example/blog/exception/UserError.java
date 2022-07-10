package com.example.blog.exception;

public class UserError extends RuntimeException {
    public UserError(String message) {
        super(message);
    }
}
