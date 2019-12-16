package com.domain.member.service.jpa;

import com.domain.member.repository.jpa.MemberCommonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(transactionManager = "jpaTransactionManager")
public class MemberValidatior implements MemberValidationService{

    private MemberCommonRepository memberCommonRepository;

    public MemberValidatior(MemberCommonRepository memberCommonRepository){
        this.memberCommonRepository = memberCommonRepository;
    }

    @Override
    public boolean duplicationCheckEmail(String email) {
        Optional<Integer> result = memberCommonRepository.findMemberByMemberEmail(email);
        if(result.isPresent()){
            return false;
        }
        return true;
    }

    @Override
    public boolean duplicationCheckMemberName(String memberName) {
        Optional<Integer> result = memberCommonRepository.findMemberByMemberName(memberName);
        if(result.isPresent()){
            return false;
        }
        return true;
    }
}
