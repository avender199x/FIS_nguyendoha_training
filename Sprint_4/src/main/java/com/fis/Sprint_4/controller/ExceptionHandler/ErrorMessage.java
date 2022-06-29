package com.fis.Sprint_4.controller.ExceptionHandler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private String code;
    private String message;
}
