package com.domain.global.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ResponseErrors  {

    private String message;
    private int httpStatus;
    private String code;

    private List<ErrorInformation> responseErrorsList = new ArrayList<>();

    public ResponseErrors(Errors errors){
        for(FieldError fieldError : errors.getFieldErrors()){
            ErrorInformation errorInformation = new ErrorInformation();
            errorInformation.setField(fieldError.getObjectName());
            errorInformation.setValue(fieldError.getField());
            errorInformation.setReason(fieldError.getDefaultMessage());
            responseErrorsList.add(errorInformation);
        }
    }

    public ResponseErrors(ErrorCode errorCode){
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
        this.code = errorCode.getCode();
    }


    @Setter
    @Getter
    private class ErrorInformation{
        private String field;
        private String value;
        private String reason;
    }


}
