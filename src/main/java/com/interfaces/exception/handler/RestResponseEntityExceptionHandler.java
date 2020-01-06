package com.interfaces.exception.handler;

import com.interfaces.exception.DuplicatedEntityException;
import com.interfaces.exception.EntityNotFoundException;
import com.interfaces.exception.MethodNotSupportedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ResponseErrors> canNotFoundContent(EntityNotFoundException e) {

        log.error(e.getMessage());
        ResponseErrors responseErrors = new ResponseErrors(e.getErrorCode());

        return new ResponseEntity(responseErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DuplicatedEntityException.class)
    public ResponseEntity<ResponseErrors> duplicatedEntity(DuplicatedEntityException e) {

        log.error(e.getMessage());
        ResponseErrors responseErrors = new ResponseErrors(e.getErrorCode());

        return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodNotSupportedException.class)
    public ResponseEntity notSupportedMethod(MethodNotSupportedException e) {

        log.error(e.getMessage());
        ResponseErrors responseErrors = new ResponseErrors(e.getErrorCode());

        return new ResponseEntity(responseErrors.getResponseErrorsList(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    //Exception 추가

}
