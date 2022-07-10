package com.example.blog.controller.ExceptionHandler;

import com.example.blog.constant.Constant;
import com.example.blog.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Message> UserNotFoundException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Message.builder()
                        .code(Constant.USER_NOT_FOUND)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {UserError.class})
    public ResponseEntity<Message> UserError(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.USER_ERROR)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {UserNameException.class})
    public ResponseEntity<Message> UserNameException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.USER_NAME_EXCEPTION)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {UserPhoneException.class})
    public ResponseEntity<Message> UserPhoneException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.USER_PHONE_EXCEPTION)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {PostsNotFoundException.class})
    public ResponseEntity<Message> PostsNotFoundException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Message.builder()
                        .code(Constant.POSTS_NOT_FOUND)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {PostsError.class})
    public ResponseEntity<Message> PostsError(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.POSTS_ERROR)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {PostsTitleException.class})
    public ResponseEntity<Message> PostsTitleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.POSTS_TITLE_EXCEPTION)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {PostsCharactersException.class})
    public ResponseEntity<Message> PostsCharactersException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.POSTS_CHARACTERS_EXCEPTION)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {GroupNotFoundException.class})
    public ResponseEntity<Message> GroupNotFoundException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Message.builder()
                        .code(Constant.GROUP_NOT_FOUND)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {GroupNameException.class})
    public ResponseEntity<Message> GroupNameException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.GROUP_NAME_EXCEPTION)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {GroupError.class})
    public ResponseEntity<Message> GroupError(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.GROUP_ERROR)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {CommentNotFound.class})
    public ResponseEntity<Message> CommentNotFound(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Message.builder()
                        .code(Constant.COMMENT_NOT_FOUND)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {CommentError.class})
    public ResponseEntity<Message> CommentError(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.COMMENT_ERROR)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {CommentCharactersException.class})
    public ResponseEntity<Message> CommentCharactersException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Message.builder()
                        .code(Constant.COMMENT_CHARACTERS_EXCEPTION)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Message> InternalServerError(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Message.builder()
                        .code(Constant.INTERNAL_SERVER_ERROR)
                        .message(e.getMessage())
                        .build());
    }
}
