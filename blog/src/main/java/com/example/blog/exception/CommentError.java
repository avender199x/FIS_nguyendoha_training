package com.example.blog.exception;

public class CommentError extends RuntimeException {
    public CommentError(String message) {
        super(message);
    }
}
