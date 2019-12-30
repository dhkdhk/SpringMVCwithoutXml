package com.domain.member.exception;

import com.domain.global.error.EntityNotFoundException;
import com.domain.global.error.ErrorCode;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(final Long target){
        super(target+"에 해당되는 사용자를 찾지 못했습니다.", ErrorCode.MEMBER_NOT_FOUND);
    }

}
