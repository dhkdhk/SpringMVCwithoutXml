package com.domain;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addMemeber(Member member) {

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
