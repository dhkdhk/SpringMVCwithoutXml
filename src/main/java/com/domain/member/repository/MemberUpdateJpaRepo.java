package com.domain.member.repository;

import com.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("jpaRepository")
public interface MemberUpdateJpaRepo extends JpaRepository<Member, Long>{

}
