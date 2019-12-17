package com.domain.member.repository.jpa;

import com.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCommonRepository extends JpaRepository<Member, Long>{

    boolean existsMemberByMemberEmail(String email);
}
