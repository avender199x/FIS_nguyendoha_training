package com.example.blog.exception;

public class CommentCharactersException extends RuntimeException {
    public CommentCharactersException(String message) {
        super(message);
    }
}
