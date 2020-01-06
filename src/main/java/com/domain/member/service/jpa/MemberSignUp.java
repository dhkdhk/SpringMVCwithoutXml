package com.domain.member.service.jpa;

import com.interfaces.exception.handler.ErrorCode;
import com.interfaces.exception.DuplicatedEntityException;
import com.interfaces.exception.MethodNotSupportedException;
import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "memberAccountSignUp")
@Transactional
@RequiredArgsConstructor
public class MemberSignUp implements MemberAccount {

    private final MemberCommonRepository memberCommonRepository;

    @Override
    public Member ourHomePageToSignUp(final Member member) {

        verifyDuplicateEmail(member.getMemberEmail());
        verifyDuplicatePhoneNumber(member.getMemberPhoneNumber());

        Member resultMember = memberCommonRepository.save(member);

        resultMember.responseNotShowPassword();
        return resultMember;

    }

    private void verifyDuplicateEmail(final String email) {
        Optional<Member> optionalMember = memberCommonRepository.findByMemberEmail(email);
        if (optionalMember.isPresent()) {
            throw new DuplicatedEntityException(email, ErrorCode.DUPLICATION_FIELD);
        }
    }

    private void verifyDuplicatePhoneNumber(final String phoneNumber) {
        Optional<Member> optionalMember = memberCommonRepository.findByMemberPhoneNumber(phoneNumber);
        if (optionalMember.isPresent()) {
            throw new DuplicatedEntityException(phoneNumber, ErrorCode.DUPLICATION_FIELD);
        }
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
