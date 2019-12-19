package com.domain.globalutill;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
public class ResponseErrors  {

    private List<ErrorInformation> responseErrorsList = new ArrayList<>();

    public ResponseErrors(Errors result){
        this.setErrorsToList(result);
    }

    private void setErrorsToList(Errors result){
        for(FieldError fieldError : result.getFieldErrors()){
            ErrorInformation errorInformaation = new ErrorInformation();
            errorInformaation.setErrorName(fieldError.getField());
            errorInformaation.setMessage(fieldError.getDefaultMessage());
            responseErrorsList.add(errorInformaation);
        }
    }

    @Setter @Getter
    private class ErrorInformation{
        private String errorName;
        private String message;
    }


}
