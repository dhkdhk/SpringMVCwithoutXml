package com.domain.global.error;

import lombok.Getter;

@Getter
public class MethodNotSupportedException extends RuntimeException {

    private ErrorCode errorCode;

    public MethodNotSupportedException(final String errorMessage, ErrorCode errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }

}
