package com.nguyendoha.Test_final_fis.controller.ExceptionHandler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private String code;
    private String message;
}
