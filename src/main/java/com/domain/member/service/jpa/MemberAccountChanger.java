package com.domain.member.service.jpa;

import com.domain.member.entity.Member;
import com.domain.member.exception.MemberNotFoundException;
import com.domain.member.repository.jpa.MemberCommonRepository;
import com.interfaces.exception.DuplicatedEntityException;
import com.interfaces.exception.handler.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberAccountChanger implements MemberAccount, MemberSignUp {

    private final MemberCommonRepository memberCommonRepository;

    @Override
    public Member signUp(final Member member) {

        verifyDuplicateEmail(member.getMemberEmail());
        verifyDuplicatePhoneNumber(member.getMemberPhoneNumber());

        return memberCommonRepository.save(member);
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
    public void changePassword(Long memberId, String password) {
        //TODO 구현 필요
    }


    @Override
    public void deleteMember(final Long memberId) {
        Optional<Member> memberOptional = memberCommonRepository.findById(memberId);
        if(memberOptional.isEmpty()){
            throw new MemberNotFoundException("Member.meberId", memberId);
        }
        memberCommonRepository.deleteById(memberId);
    }




}
