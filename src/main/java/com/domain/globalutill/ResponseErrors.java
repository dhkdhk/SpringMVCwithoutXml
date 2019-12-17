package com.domain.globalutill;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ResponseErrors  {

    private List<ErrorInformation> responseErrorsList = new ArrayList<>();

    public ResponseErrors(BindingResult bindingResult){
        this.setErrorsToList(bindingResult);
    }

    private void setErrorsToList(BindingResult bindingResult){
        ErrorInformation errorInformaation = new ErrorInformation();
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            errorInformaation.setErrorName(fieldError.getField());
            errorInformaation.setMessage(fieldError.getDefaultMessage());
            responseErrorsList.add(errorInformaation);
        }
    }

    @Setter
    private class ErrorInformation{
        private String errorName;
        private String message;
    }


}
