package com.fis.Sprint_4.controller.ExceptionHandler;

import com.fis.Sprint_4.controller.ExceptionHandler.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.fis.Sprint_4.constant.Constant.*;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {PersonNotFoundException.class})
    protected ResponseEntity<ErrorMessage> PersonNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(PERSON_NOT_FOUND).code(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {StorageNotFoundException.class})
    protected ResponseEntity<ErrorMessage> StorageNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(STORAGE_NOT_FOUND).code(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {TrackEntryNotFoundException.class})
    protected ResponseEntity<ErrorMessage> TrackEntryNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(TRACK_ENTRY_NOT_FOUND).code(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {TrackEntryErrorException.class})
    protected ResponseEntity<ErrorMessage> TrackEntryErrorException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().message(TRACK_ENTRY_ERROR).code(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {EvidenceNotFoundException.class})
    protected ResponseEntity<ErrorMessage> EvidenceNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(EVIDENCE_NOT_FOUND).code(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {DetectiveNotFoundException.class})
    protected ResponseEntity<ErrorMessage> DetectiveNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(DETECTIVE_NOT_FOUND).code(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {CriminalCaseNotFoundException.class})
    protected ResponseEntity<ErrorMessage> CriminalCaseNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(CRIMINAL_CASE_NOT_FOUND).code(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {DetectiveErrorException.class})
    protected ResponseEntity<ErrorMessage> DetectiveErrorException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().message(DETECTIVE_ERROR).code(exception.getMessage()).build());
    }
}
