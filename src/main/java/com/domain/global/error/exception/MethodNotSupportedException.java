package com.domain.global.error.exception;

import com.domain.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class MethodNotSupportedException extends RuntimeException {

    private ErrorCode errorCode;

    public MethodNotSupportedException(final String errorMessage, ErrorCode errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }

}
