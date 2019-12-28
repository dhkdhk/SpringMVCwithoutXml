package com.domain.member.utill.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> handleConflict(IllegalArgumentException exception) {
        RuntimeException e = exception;
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
