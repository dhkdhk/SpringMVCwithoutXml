package com.domain.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    MEMBER_NOT_FOUND("MEMBER", "MEMERNOT_NOT_FOUND", "원하는 사용자를 찾지못했습니다."),

    METHOD_NOT_SUPPORT("METHOD", "NOT_SUPPORTED", "지원되지 않는메서드입니다.");

    private String errorObject;
    private String rejectValue;
    private String message;

    ErrorCode(final String errorObject, final String rejectValue, final String message) {
        this.errorObject = errorObject;
        this.rejectValue = rejectValue;
        this.message = message;
    }
}
