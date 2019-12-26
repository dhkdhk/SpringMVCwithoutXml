package com.domain.member.service;

import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFinderService implements UserDetailsService {


    private final MemberCommonRepository memberCommonRepository;


    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        final Member member = memberCommonRepository.findMemberByMemberEmail(memberEmail).orElseThrow(
                () -> new IllegalArgumentException(memberEmail + " is not founded"));

        return User.builder()
                .username(member.getMemberEmail())
                .password(member.getMemberPassword())
                .build();

    }

}
