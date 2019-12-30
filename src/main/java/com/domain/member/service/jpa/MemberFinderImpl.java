package com.domain.member.service.jpa;


import com.domain.member.entity.Member;
import com.domain.member.exception.MemberNotFoundException;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "jpaTransactionManager")
@RequiredArgsConstructor
public class MemberFinderImpl implements MemberFinder {

    private final MemberCommonRepository memberCommonRepository;

    @Override
    public Member findById(final Long memberId) {
        final Member member = memberCommonRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        member.responseNotShowPassword();
        return member;
    }
}
