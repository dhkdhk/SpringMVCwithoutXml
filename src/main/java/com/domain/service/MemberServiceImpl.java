package com.domain.service;

import com.domain.entity.Member;
import com.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public int addMemeber(Member member) {
        return memberRepository.addMemeber(member);
    }

    @Override
    public void updateMember() {

    }

    @Override
    public Member getMember() {
        return null;
    }

    @Override
    public void deleteMember() {

    }
}
