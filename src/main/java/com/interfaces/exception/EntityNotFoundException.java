package com.interfaces.exception;

import com.interfaces.exception.handler.ErrorCode;
import com.interfaces.exception.handler.ResponseErrors;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private ResponseErrors errors;
    private ErrorCode errorCode = ErrorCode.ENTITY_NOT_FOUND;

    public EntityNotFoundException(String field, Long targetValue, String reason) {
        super(reason);
        this.setUpErrors(field, targetValue.toString(), reason);
    }

    private void setUpErrors(String field, String targetValue, String reason){
        errors = new ResponseErrors(errorCode, field, targetValue, reason);
    }

}
