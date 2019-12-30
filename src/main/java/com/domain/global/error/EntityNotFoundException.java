package com.domain.global.error;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{

    private ErrorCode errorCode;

    public EntityNotFoundException(final String errorMessage, ErrorCode errorCode){
       super(errorMessage);
       this.errorCode = errorCode;
    }

}
