package com.domain.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    MEMBER_NOT_FOUND("MEMBER", "MEMERNOT_NOT_FOUND", "원하는 사용자를 찾지못했습니다.");

    private String objectName;
    private String rejectValue;
    private String errorMessage;

    ErrorCode(final String objectName, final String rejectValue, final String errorMessage) {
        this.objectName = objectName;
        this.rejectValue = rejectValue;
        this.errorMessage = errorMessage;
    }
}
