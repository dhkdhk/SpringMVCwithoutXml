package com.domain.member.exception;

import com.interfaces.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {


    public MemberNotFoundException(final String field, final Long targetValue)  {
        super(field, targetValue, targetValue + "에 해당되는 사용자를 찾지 못했습니다.");

    }
}
