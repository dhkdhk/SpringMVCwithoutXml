package com.domain.member.service.jpa;

public interface MemberValidationService {

    boolean duplicationCheckEmail(String email);

    boolean duplicationCheckMemberName(String memberName);


}
