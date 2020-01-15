package com.domain.member.service.jpa;

import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberPrincipleFinder implements UserDetailsService {

    private final MemberCommonRepository memberCommonRepository;

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        final Member member = memberCommonRepository.findByMemberEmail(memberEmail).orElseThrow(
                () -> new UsernameNotFoundException(memberEmail + " is not founded"));

        final List<SimpleGrantedAuthority> grantedAuthorities = member.getGrantedAuthority().stream()
                .map(authorities -> new SimpleGrantedAuthority(authorities))
                .collect(Collectors.toList());

        return User.builder()
                .username(member.getMemberEmail())
                .password(member.getMemberPassword())
                .authorities(grantedAuthorities)
                .build();

    }

}
