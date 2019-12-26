package com.domain.member.repository.jpa;

import com.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberCommonRepository extends JpaRepository<Member, Long>{

    Optional<Member> findMemberByMemberEmail(String email);

    @Modifying
    @Query(value="UPDATE Member m SET m.memberName = :#{#member.memberName} " +
            "                       , m.memberEmail =:#{#member.memberEmail}" +
            "                       , m.memberAddress =:#{#member.memberAddress} WHERE m.memberId = :#{#member.memberId}")
    int updateMemberInformation(@Param("member") Member member);


}
