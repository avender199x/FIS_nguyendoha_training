package com.example.blog.controller.ExceptionHandler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String code;
    private String message;
}
