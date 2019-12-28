package com.domain.member.service.jpa;

import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
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
}
