package com.interfaces.exception;

import com.interfaces.exception.handler.ErrorCode;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public EntityNotFoundException(final String errorMessage, ErrorCode errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

}
