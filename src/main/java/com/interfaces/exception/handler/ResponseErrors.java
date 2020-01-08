package com.interfaces.exception.handler;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ResponseErrors {

    private String message;
    private int httpStatus;
    private String code;

    private List<ErrorInformation> errors = new ArrayList<>();

    public ResponseErrors(ErrorCode errorCode, String field, String targetValue, String reason){
        this(errorCode);
        ErrorInformation errorInformation = new ErrorInformation(field, targetValue, reason);
        errors.add(errorInformation);
    }

    public ResponseErrors(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
        this.code = errorCode.getCode();
    }


    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    private class ErrorInformation {
        private String field;
        private String value;
        private String reason;

    }


}
