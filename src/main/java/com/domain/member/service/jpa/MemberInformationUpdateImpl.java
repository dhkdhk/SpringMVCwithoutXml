package com.domain.member.service.jpa;

import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(transactionManager="jpaTransactionManager")
public class MemberInformationUpdateImpl implements MemberInformationUpdate {

    private final MemberCommonRepository memberCommonRepository;


    @Override
    public int updateMemberInformation(Member member) {
      return  memberCommonRepository.updateMemberInformation(member);
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
