package com.domain;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void addMemeber(Member member) {
        memberRepository.addMemeber(member);
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
