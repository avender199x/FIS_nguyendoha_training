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
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorMessage> SERVER_ERROR(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorMessage.builder().message(exception.getMessage()).code(SERVER_ERROR).build());
    }

    @ExceptionHandler(value = {PersonNotFoundException.class})
    protected ResponseEntity<ErrorMessage> PersonNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(exception.getMessage()).code(PERSON_NOT_FOUND).build());
    }

    @ExceptionHandler(value = {StorageNotFoundException.class})
    protected ResponseEntity<ErrorMessage> StorageNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(exception.getMessage()).code(STORAGE_NOT_FOUND).build());
    }

    @ExceptionHandler(value = {TrackEntryNotFoundException.class})
    protected ResponseEntity<ErrorMessage> TrackEntryNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(exception.getMessage()).code(TRACK_ENTRY_NOT_FOUND).build());
    }

    @ExceptionHandler(value = {TrackEntryErrorException.class})
    protected ResponseEntity<ErrorMessage> TrackEntryErrorException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().message(exception.getMessage()).code(TRACK_ENTRY_ERROR).build());
    }

    @ExceptionHandler(value = {EvidenceNotFoundException.class})
    protected ResponseEntity<ErrorMessage> EvidenceNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(EVIDENCE_NOT_FOUND).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {EvidenceErrorException.class})
    protected ResponseEntity<ErrorMessage> EvidenceErrorException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(EVIDENCE_ERROR).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {DetectiveNotFoundException.class})
    protected ResponseEntity<ErrorMessage> DetectiveNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(DETECTIVE_NOT_FOUND).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {CriminalCaseNotFoundException.class})
    protected ResponseEntity<ErrorMessage> CriminalCaseNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(CRIMINAL_CASE_NOT_FOUND).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {CriminalCaseErrorException.class})
    protected ResponseEntity<ErrorMessage> CriminalCaseErrorException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().code(CRIMINAL_CASE_ERROR).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {DetectiveErrorException.class})
    protected ResponseEntity<ErrorMessage> DetectiveErrorException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().code(DETECTIVE_ERROR).message(exception.getMessage()).build());
    }
}
