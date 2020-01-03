package com.domain.global.error.exception;

import com.domain.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{

    private ErrorCode errorCode;

    public EntityNotFoundException(final String errorMessage, ErrorCode errorCode){
       super(errorMessage);
       this.errorCode = errorCode;
    }

}
