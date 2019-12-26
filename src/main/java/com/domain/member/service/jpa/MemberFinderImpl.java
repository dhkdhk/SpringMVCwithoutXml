package com.domain.member.service.jpa;


import com.domain.member.entity.Member;
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
    public Member findMemberByMemberEmail(String email) {
        final Member member = memberCommonRepository.findMemberByMemberEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email + " 일치하는 정보가 없습니다."));
        return member;
    }

    @Override
    public Member findById(Long memberId) {
        final Member member = memberCommonRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException(memberId + " 일치하는 정보가 없습니다."));
        return member;
    }
}
