package com.domain.member.service.jpa;

import com.domain.global.error.ErrorCode;
import com.domain.global.error.MethodNotSupportedException;
import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "memberAccountSignUp")
@RequiredArgsConstructor
public class MemberSignUp implements MemberAccount {

    private final MemberCommonRepository memberCommonRepository;

    @Override
    public Member ourHomePageToSignUp(final Member member) {
        final Member resultMember = memberCommonRepository.save(member);
        resultMember.responseNotShowPassword();
        return resultMember;
    }

    @Override
    public boolean deleteMember(final Long memberId) {
        throw new MethodNotSupportedException("지원되지 않는 호출입니다.", ErrorCode.METHOD_NOT_SUPPORT);
    }

    @Override
    public void changePassword(final Long memberId, final String password) {
        throw new MethodNotSupportedException("지원되지 않는 호출입니다.", ErrorCode.METHOD_NOT_SUPPORT);
    }


}
