package com.domain.member.exception;

import com.interfaces.exception.EntityNotFoundException;
import com.interfaces.exception.handler.ErrorCode;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(final Long target) {
        super(target + "에 해당되는 사용자를 찾지 못했습니다.", ErrorCode.ENTITY_NOT_FOUND);
    }

}
