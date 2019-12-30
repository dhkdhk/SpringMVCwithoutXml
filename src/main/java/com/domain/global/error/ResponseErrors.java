package com.domain.global.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ResponseErrors  {

    private List<ErrorInformation> responseErrorsList = new ArrayList<>();

    public ResponseErrors(Errors errors){
        this.ofErrors(errors);
    }

    public ResponseErrors(ErrorCode errorCode){
        this.ofErrors(errorCode);
    }


    private void ofErrors(Errors errors){
        for(FieldError fieldError : errors.getFieldErrors()){
            ErrorInformation errorInformation = new ErrorInformation();
            errorInformation.setErrorObject(fieldError.getObjectName());
            errorInformation.setRejectValue(fieldError.getField());
            errorInformation.setMessage(fieldError.getDefaultMessage());
            responseErrorsList.add(errorInformation);
        }
    }

    private void ofErrors(ErrorCode errorCode){
        ErrorInformation errorInformation = new ErrorInformation();
        errorInformation.setErrorObject(errorCode.getObjectName());
        errorInformation.setRejectValue(errorCode.getRejectValue());
        errorInformation.setMessage(errorCode.getErrorMessage());
        responseErrorsList.add(errorInformation);
    }



    @Setter
    private class ErrorInformation{
        private String errorObject;
        private String rejectValue;
        private String message;
    }


}
