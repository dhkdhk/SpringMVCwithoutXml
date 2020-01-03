package com.domain.member.service.jpa;

import com.domain.global.error.ErrorCode;
import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import com.domain.global.error.exception.MethodNotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "memberAccountChanger")
@RequiredArgsConstructor
@Transactional(transactionManager="jpaTransactionManager")
public class MemberAccountChanger implements MemberAccount{

    private final MemberCommonRepository memberCommonRepository;

    @Override
    public boolean deleteMember(Long memberId) {
        Optional<Member> memberOptional = memberCommonRepository.findById(memberId);
        return memberOptional
                .map(member -> { memberCommonRepository.deleteById(memberId);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public void changePassword(Long memberId, String password) {
        throw new MethodNotSupportedException("지원되지 않는 호출입니다.", ErrorCode.METHOD_NOT_SUPPORT);
    }

    @Override
    public Member ourHomePageToSignUp(Member member) {
        throw new MethodNotSupportedException("지원되지 않는 호출입니다.", ErrorCode.METHOD_NOT_SUPPORT);
    }
}
