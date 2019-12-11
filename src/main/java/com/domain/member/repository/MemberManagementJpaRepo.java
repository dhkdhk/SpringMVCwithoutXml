package com.domain.member.repository;

import com.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberManagementJpaRepo extends JpaRepository<Member, Long>{

}
