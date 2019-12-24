package com.domain.member.service.jpa;

import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(transactionManager="jpaTransactionManager")
public class MemberUpdator implements MemberInformationUpdateService {

    private final MemberCommonRepository memberCommonRepository;

    @Override
    public Member addMember(Member member) {
        return memberCommonRepository.save(member);
    }

    @Override
    public Optional<Member> updateMember() {
        throw new IllegalArgumentException("updateMember는 유효하지 않는 요청입니다.");
    }

    @Override
    public Member getMember(Long memberId) {
        final Member member = memberCommonRepository.findById(memberId)
                                        .orElseThrow(() -> new IllegalArgumentException(memberId + " 일치하는 정보가 없습니다."));
        return  member;
    }

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
