package com.example.blog.exception;

public class PostsError extends RuntimeException {
    public PostsError(String message) {
        super(message);
    }
}
