package com.interfaces.exception.handler;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ENTITY_NOT_FOUND(400, "M001", "Can not find Entity"),

    METHOD_NOT_SUPPORT(405, "C001", "Not Supported Method"),

    DUPLICATION_FIELD(400, "C002", "Duplicated Field");

    private int httpStatus;
    private String code;
    private String message;

    ErrorCode(final int httpStatus, final String code, final String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
